package com.sinaseyfi.domain.models


data class RepositoryCollaboratorDataModel(
    override val id: Long,
    override val avatarUrl: String,
    override val login: String,
    override val htmlUrl: String,
    val permissions: CollaboratorPermissionsDataModel
): BaseUser