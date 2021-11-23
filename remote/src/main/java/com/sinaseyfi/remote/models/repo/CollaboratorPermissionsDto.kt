package com.sinaseyfi.remote.models.repo

import com.google.gson.annotations.SerializedName
import com.sinaseyfi.remote.ADMIN
import com.sinaseyfi.remote.PULL
import com.sinaseyfi.remote.PUSH
import com.sinaseyfi.remote.base.Dto

data class CollaboratorPermissionsDto(
    @SerializedName(PUSH)
    val push: Boolean,
    @SerializedName(PULL)
    val pull: Boolean,
    @SerializedName(ADMIN)
    val admin: Boolean
): Dto