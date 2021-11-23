package com.sinaseyfi.domain.models

data class RepositoryOwnerDataModel(
    override val id: Long,
    override val avatarUrl: String,
    override val login: String,
    override val htmlUrl: String
): BaseUser