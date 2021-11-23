package com.sinaseyfi.domain.usecases

import com.sinaseyfi.domain.base.BaseFlowUseCase
import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.domain.repositories.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRepositoriesFlowUseCase @Inject constructor(
    private val repoRepository: RepoRepository
): BaseFlowUseCase<Any?, List<RepositoryDataModel>>() {
    override fun perform(request: Any?): Flow<List<RepositoryDataModel>> =
        repoRepository.getRepositoryListFlow().flowOn(Dispatchers.Main)
}