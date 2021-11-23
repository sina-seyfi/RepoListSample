package com.sinaseyfi.remote.models.repo.mappers

import com.sinaseyfi.domain.models.RepositoryCollaboratorDataModel
import com.sinaseyfi.remote.base.DtoMapper
import com.sinaseyfi.remote.models.repo.RepositoryCollaboratorDto
import javax.inject.Inject

class RepositoryCollaboratorDtoMapper @Inject constructor(
    private val collaboratorPermissionsDtoMapper: CollaboratorPermissionsDtoMapper
): DtoMapper<RepositoryCollaboratorDto, RepositoryCollaboratorDataModel> {
    override fun mapToDataModel(dto: RepositoryCollaboratorDto): RepositoryCollaboratorDataModel =
        RepositoryCollaboratorDataModel(
            dto.id,
            dto.avatarUrl,
            dto.login,
            dto.htmlUrl,
            collaboratorPermissionsDtoMapper.mapToDataModel(dto.permissions)
        )
}