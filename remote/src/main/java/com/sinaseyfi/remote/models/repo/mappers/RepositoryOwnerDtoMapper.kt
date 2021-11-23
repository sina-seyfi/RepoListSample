package com.sinaseyfi.remote.models.repo.mappers

import com.sinaseyfi.domain.models.RepositoryOwnerDataModel
import com.sinaseyfi.remote.base.DtoMapper
import com.sinaseyfi.remote.models.repo.RepositoryOwnerDto
import javax.inject.Inject

class RepositoryOwnerDtoMapper @Inject constructor()
    : DtoMapper<RepositoryOwnerDto, RepositoryOwnerDataModel> {

    override fun mapToDataModel(dto: RepositoryOwnerDto): RepositoryOwnerDataModel =
        RepositoryOwnerDataModel(
            dto.id,
            dto.avatarUrl,
            dto.login,
            dto.htmlUrl
        )

}