package com.sinaseyfi.data.sources.remote

import androidx.paging.PagingSource
import com.sinaseyfi.data.base.RemoteDataSource
import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.domain.models.request.RepositoryCollaboratorsRequestDataModel
import com.sinaseyfi.domain.models.request.RepositoryDetailsRequestDataModel

interface RepoRemoteDataSource: RemoteDataSource {

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
    suspend fun fetchRepositoryList(since: Long): List<RepositoryDataModel>
    suspend fun fetchRepositoryDetails(repositoryDetailsRequestDataModel: RepositoryDetailsRequestDataModel): RepositoryDetailsDataModel


    // Not working because of paging library problem.
    fun fetchRepositoryCollaboratorsPaging(
        repositoryCollaboratorsRequestDataModel: RepositoryCollaboratorsRequestDataModel
    ): PagingSource<Int, RepositoryCollaboratorDataModel>

}