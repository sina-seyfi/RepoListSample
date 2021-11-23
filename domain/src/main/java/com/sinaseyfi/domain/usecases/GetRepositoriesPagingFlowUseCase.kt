package com.sinaseyfi.domain.usecases

import androidx.paging.PagingData
import com.sinaseyfi.domain.base.BasePagingFlowUseCase
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesPagingFlowUseCase @Inject constructor(
    private val repoRepository: RepoRepository
) : BasePagingFlowUseCase<Any?, RepositoryDataModel>() {

    override fun perform(request: Any?): Flow<PagingData<RepositoryDataModel>> =
        repoRepository.getRepositoryListPagingFlow()

}