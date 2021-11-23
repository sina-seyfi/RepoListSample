package com.sinaseyfi.remote.models.repo.mappers

import com.sinaseyfi.domain.models.RepositoryDataModel
import com.sinaseyfi.remote.base.DtoMapper
import com.sinaseyfi.remote.models.repo.RepositoryDto
import javax.inject.Inject

class RepositoryDtoMapper @Inject constructor(
    private val repositoryOwnerDtoMapper: RepositoryOwnerDtoMapper
): DtoMapper<RepositoryDto, RepositoryDataModel> {
    override fun mapToDataModel(dto: RepositoryDto): RepositoryDataModel =
        RepositoryDataModel(
            dto.id,
            dto.name,
            repositoryOwnerDtoMapper.mapToDataModel(dto.owner),
            dto.privat3,
            dto.htmlUrl,
            dto.description
        )
}