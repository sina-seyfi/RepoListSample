package com.sinaseyfi.remote.models.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sinaseyfi.data.sources.remote.RepoRemoteDataSource
import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.domain.models.request.RepositoryCollaboratorsRequestDataModel
import com.sinaseyfi.domain.models.request.RepositoryDetailsRequestDataModel
import com.sinaseyfi.remote.base.BaseApiService
import com.sinaseyfi.remote.models.repo.mappers.RepositoryCollaboratorDtoMapper
import com.sinaseyfi.remote.models.repo.mappers.RepositoryDetailsDtoMapper
import com.sinaseyfi.remote.models.repo.mappers.RepositoryDtoMapper
import javax.inject.Inject

class RepoApiService @Inject constructor(
    private val repositoryDtoMapper: RepositoryDtoMapper,
    private val repositoryDetailsDtoMapper: RepositoryDetailsDtoMapper,
    private val repositoryCollaboratorDtoMapper: RepositoryCollaboratorDtoMapper
) : BaseApiService<RepoEndpoint>(RepoEndpoint::class.java), RepoRemoteDataSource{

    override suspend fun fetchRepositoryList(since: Long): List<RepositoryDataModel> =
        executionWithMessage {
            apiService.fetchRepositories(since)
        }.data?.map { dto ->
            repositoryDtoMapper.mapToDataModel(dto)
        } ?: emptyList()

    override suspend fun fetchRepositoryDetails(repositoryDetailsRequestDataModel: RepositoryDetailsRequestDataModel): RepositoryDetailsDataModel =
        repositoryDetailsDtoMapper.mapToDataModel(
            executionWithMessage {
                apiService.fetchRepository(
                    repositoryDetailsRequestDataModel.ownerName,
                    repositoryDetailsRequestDataModel.repoName
                )
            }.data!!
        )

    override fun fetchRepositoryCollaboratorsPaging(
        repositoryCollaboratorsRequestDataModel: RepositoryCollaboratorsRequestDataModel
    ): PagingSource<Int, RepositoryCollaboratorDataModel> = object: PagingSource<Int, RepositoryCollaboratorDataModel>() {

        override fun getRefreshKey(state: PagingState<Int, RepositoryCollaboratorDataModel>): Int? = null

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryCollaboratorDataModel> {
            val nextPageNumber = params.key ?: 1
            return try {
                val list = executionWithMessage {
                    apiService.fetchCollaborators(
                        repositoryCollaboratorsRequestDataModel.ownerName,
                        repositoryCollaboratorsRequestDataModel.repoName,
                        "outside",
                        params.loadSize, nextPageNumber
                    )
                }.data?.map { dto ->
                    repositoryCollaboratorDtoMapper.mapToDataModel(dto)
                } ?: emptyList()
                val nextKey = if (list.isEmpty()) { null } else { nextPageNumber + 1 }
                LoadResult.Page(
                    data = list,
                    prevKey = null, // Only paging forward.
                    // assume that if a full page is not loaded, that means the end of the data
                    nextKey = nextKey
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

    }

}