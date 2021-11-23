package com.sinaseyfi.domain.repositories

import androidx.paging.PagingData
import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.domain.models.request.RepositoryCollaboratorsRequestDataModel
import com.sinaseyfi.domain.models.request.RepositoryDetailsRequestDataModel
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    suspend fun updateRepositoryList(since: Long)
    fun getRepositoryListFlow(): Flow<List<RepositoryDataModel>>
    suspend fun getRepositoryDetails(repositoryDetailsRequestDataModel: RepositoryDetailsRequestDataModel): RepositoryDetailsDataModel
    fun getRepositoryCollaborators(
        repositoryCollaboratorsRequestDataModel: RepositoryCollaboratorsRequestDataModel
    ): Flow<PagingData<RepositoryCollaboratorDataModel>>

    // Not working section. Because of paging library problem...
    fun getRepositoryListPagingFlow(): Flow<PagingData<RepositoryDataModel>>

}