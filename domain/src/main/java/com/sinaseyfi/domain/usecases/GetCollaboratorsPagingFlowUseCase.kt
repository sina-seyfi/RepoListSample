package com.sinaseyfi.domain.usecases

import androidx.paging.PagingData
import com.sinaseyfi.domain.base.BasePagingFlowUseCase
import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.domain.models.request.RepositoryCollaboratorsRequestDataModel
import com.sinaseyfi.domain.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCollaboratorsPagingFlowUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) : BasePagingFlowUseCase<RepositoryCollaboratorsRequestDataModel, RepositoryCollaboratorDataModel>() {
    override fun perform(request: RepositoryCollaboratorsRequestDataModel): Flow<PagingData<RepositoryCollaboratorDataModel>> =
        repoRepository.getRepositoryCollaborators(request)
}