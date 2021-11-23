package com.sinaseyfi.domain.usecases

import com.sinaseyfi.domain.base.SingleUseCase
import com.sinaseyfi.domain.base.UseCase
import com.sinaseyfi.domain.base.UseCaseResult
import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.domain.models.request.RepositoryDetailsRequestDataModel
import com.sinaseyfi.domain.repositories.RepoRepository
import javax.inject.Inject

class GetRepositoryDetailsUseCase @Inject constructor(
    private val repoRepository: RepoRepository
): SingleUseCase<RepositoryDetailsRequestDataModel, RepositoryDetailsDataModel>() {
    override suspend fun task(request: RepositoryDetailsRequestDataModel): UseCaseResult<RepositoryDetailsDataModel> =
        UseCaseResult(repoRepository.getRepositoryDetails(request))
}