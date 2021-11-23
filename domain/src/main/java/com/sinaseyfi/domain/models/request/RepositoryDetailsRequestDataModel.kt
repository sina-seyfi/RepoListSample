package com.sinaseyfi.domain.models.request

import com.sinaseyfi.domain.base.RequestDataModel

data class RepositoryDetailsRequestDataModel(
    val ownerName: String,
    val repoName: String
): RequestDataModel