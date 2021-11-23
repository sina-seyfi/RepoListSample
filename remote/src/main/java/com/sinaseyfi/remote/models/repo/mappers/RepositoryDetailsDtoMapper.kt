package com.sinaseyfi.remote.models.repo.mappers

import com.sinaseyfi.domain.models.RepositoryDetailsDataModel
import com.sinaseyfi.remote.base.DtoMapper
import com.sinaseyfi.remote.models.repo.RepositoryDetailsDto
import javax.inject.Inject

class RepositoryDetailsDtoMapper @Inject constructor(
    private val repositoryOwnerDtoMapper: RepositoryOwnerDtoMapper
): DtoMapper<RepositoryDetailsDto, RepositoryDetailsDataModel> {

    override fun mapToDataModel(dto: RepositoryDetailsDto): RepositoryDetailsDataModel =
        RepositoryDetailsDataModel(
            dto.id,
            dto.name,
            dto.stargazersCount,
            dto.forks,
            dto.description,
            dto.htmlUrl,
            repositoryOwnerDtoMapper.mapToDataModel(dto.owner),
            dto.private
        )

}