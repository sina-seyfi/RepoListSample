package com.sinaseyfi.data.repositories

import androidx.paging.*
import com.sinaseyfi.data.sources.local.RepoLocalDataSource
import com.sinaseyfi.data.sources.remote.RepoRemoteDataSource
import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.domain.models.request.RepositoryCollaboratorsRequestDataModel
import com.sinaseyfi.domain.models.request.RepositoryDetailsRequestDataModel
import com.sinaseyfi.domain.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class RepoRepositoryImpl @Inject constructor(
    private val repoRemoteDataSource: RepoRemoteDataSource,
    private val repoLocalDataSource: RepoLocalDataSource
): RepoRepository {

    override suspend fun updateRepositoryList(since: Long) {
        repoLocalDataSource.cacheRepositoryList(repoRemoteDataSource.fetchRepositoryList(since))
    }

    override fun getRepositoryListFlow(): Flow<List<RepositoryDataModel>> =
        repoLocalDataSource.fetchRepositoryList()

    override suspend fun getRepositoryDetails(repositoryDetailsRequestDataModel: RepositoryDetailsRequestDataModel): RepositoryDetailsDataModel =
        repoRemoteDataSource.fetchRepositoryDetails(repositoryDetailsRequestDataModel)

    override fun getRepositoryCollaborators(
        repositoryCollaboratorsRequestDataModel: RepositoryCollaboratorsRequestDataModel
    ): Flow<PagingData<RepositoryCollaboratorDataModel>> =
        Pager(
            config = PagingConfig(
                pageSize = RepoRemoteDataSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                repoRemoteDataSource.fetchRepositoryCollaboratorsPaging(repositoryCollaboratorsRequestDataModel)
            }
        ).flow





    /*

    Testing paging library in this section

     */


    @ExperimentalPagingApi
    override fun getRepositoryListPagingFlow(): Flow<PagingData<RepositoryDataModel>> =
        Pager(
            config = PagingConfig(
                pageSize = RepoLocalDataSource.DATABASE_PAGE_SIZE,
                enablePlaceholders = true
            ),
            remoteMediator = createRepositoryListRemoteMediator(
                repoRemoteDataSource,
                repoLocalDataSource
            )
        ) { repoLocalDataSource.fetchRepositoryPaging() }.flow

    // This class will be used for updating local database with network.
    @ExperimentalPagingApi
    private fun createRepositoryListRemoteMediator(
        repoRemoteDataSource: RepoRemoteDataSource,
        repoLocalDataSource: RepoLocalDataSource
    ) = object: RemoteMediator<Long, RepositoryDataModel>() {

        override suspend fun initialize(): InitializeAction =
            InitializeAction.LAUNCH_INITIAL_REFRESH

        override suspend fun load(
            loadType: LoadType,
            state: PagingState<Long, RepositoryDataModel>
        ): MediatorResult {
            return try {
                val loadKey: Long? = when (loadType) {
                    LoadType.REFRESH -> null
                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                            ?: return MediatorResult.Success(
                                endOfPaginationReached = true
                            )
                        lastItem.id
                    }
                }
                var listIsEmpty = true
                if(loadKey != null) {
                    val list = repoRemoteDataSource.fetchRepositoryList(loadKey)
                    repoLocalDataSource.cacheRepositoryList(list)
                    listIsEmpty = list.isEmpty()
                }
                MediatorResult.Success(
                    endOfPaginationReached = listIsEmpty
                )
            } catch (e: IOException) {
                MediatorResult.Error(e)
            }
        }

    }

}