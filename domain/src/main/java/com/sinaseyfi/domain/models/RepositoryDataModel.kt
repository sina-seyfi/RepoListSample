package com.sinaseyfi.domain.models

import com.sinaseyfi.domain.base.DataModel

data class RepositoryDataModel(
    val id: Long,
    val name: String,
    val owner: RepositoryOwnerDataModel,
    val private: Boolean,
    val htmlUrl: String,
    val description: String? = null
): DataModel