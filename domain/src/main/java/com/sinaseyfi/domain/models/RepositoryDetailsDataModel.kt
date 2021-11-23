package com.sinaseyfi.domain.models

import com.sinaseyfi.domain.base.DataModel

data class RepositoryDetailsDataModel(
    val id: Long,
    val name: String,
    val stargazersCount: Int,
    val forks: Int,
    val description: String,
    val htmlUrl: String,
    val owner: RepositoryOwnerDataModel,
    val private: Boolean
): DataModel