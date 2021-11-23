package com.sinaseyfi.database.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sinaseyfi.data.sources.local.RepoLocalDataSource
import com.sinaseyfi.database.Database
import com.sinaseyfi.database.mappers.OwnerTableMapper
import com.sinaseyfi.database.mappers.RepositoryAndOwnerTableMapper
import com.sinaseyfi.database.mappers.RepositoryTableMapper
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.log.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.lang.Exception
import javax.inject.Inject

class RepoDatabaseLocalDataSourceImpl @Inject constructor(
    private val appDatabase: Database,
    private val repositoryAndOwnerTableMapper: RepositoryAndOwnerTableMapper,
    private val repositoryTableMapper: RepositoryTableMapper,
    private val ownerTableMapper: OwnerTableMapper,
    private val logger: Logger
): RepoLocalDataSource {

    override fun fetchRepositoryList(): Flow<List<RepositoryDataModel>> =
        appDatabase.database.getRepositoryDao().fetchRepositoryList().flowOn(Dispatchers.IO)
            .map { list ->
                list.map { item ->
                    repositoryAndOwnerTableMapper.mapToData(item)
                }
            }

    override suspend fun cacheRepositoryList(repos: List<RepositoryDataModel>) {
        appDatabase.database.getRepositoryDao().cacheRepositoryAndOwnerList(
            repos.map { item -> repositoryTableMapper.mapToTable(item) },
            repos.map { item -> ownerTableMapper.mapToTable(item.owner).copy(repoId = item.id) }
        )
    }

    override suspend fun clearRepositoryList() {
        appDatabase.database.clearAllTables()
    }




    /*

    NOT WORKING... Check this one later todo()

     */



    override fun fetchRepositoryPaging(): PagingSource<Long, RepositoryDataModel> = object:
        PagingSource<Long, RepositoryDataModel>() {

        override fun getRefreshKey(state: PagingState<Long, RepositoryDataModel>): Long? = null
//        {
//            return state.anchorPosition?.let { anchorPosition ->
//                val anchorPage = state.closestPageToPosition(anchorPosition)
//                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//            }
//        }

        override suspend fun load(params: LoadParams<Long>): LoadResult<Long, RepositoryDataModel> {
            val since = params.key ?: 1
            return try {
                val list = appDatabase.database.getRepositoryDao().fetchRepositoryList(since)
                    .map { item -> repositoryAndOwnerTableMapper.mapToData(item) }
                val nextKey = if(list.isEmpty()) null else { list.maxOf { item -> item.id } }
                LoadResult.Page(
                    data = list,
                    prevKey = null,
                    nextKey = nextKey
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

    }

}