package com.sinaseyfi.domain.usecases

import com.sinaseyfi.domain.base.ConsumerUseCase
import com.sinaseyfi.domain.base.UseCaseConsumerResult
import com.sinaseyfi.domain.repositories.RepoRepository
import javax.inject.Inject

class UpdateRepositoriesUseCase @Inject constructor(
    private val repoRepository: RepoRepository
): ConsumerUseCase<Long>() {

    override suspend fun task(request: Long): UseCaseConsumerResult {
        repoRepository.updateRepositoryList(request)
        return UseCaseConsumerResult()
    }

}