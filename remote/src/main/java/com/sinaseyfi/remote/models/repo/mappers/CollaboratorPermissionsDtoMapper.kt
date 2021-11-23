package com.sinaseyfi.remote.models.repo.mappers

import com.sinaseyfi.domain.models.CollaboratorPermissionsDataModel
import com.sinaseyfi.remote.base.DtoMapper
import com.sinaseyfi.remote.models.repo.CollaboratorPermissionsDto
import javax.inject.Inject

class CollaboratorPermissionsDtoMapper @Inject constructor()
    : DtoMapper<CollaboratorPermissionsDto, CollaboratorPermissionsDataModel> {

    override fun mapToDataModel(dto: CollaboratorPermissionsDto): CollaboratorPermissionsDataModel =
        CollaboratorPermissionsDataModel(dto.push, dto.pull, dto.admin)

}