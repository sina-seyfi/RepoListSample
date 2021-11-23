package com.sinaseyfi.remote.models.repo

import com.google.gson.annotations.SerializedName
import com.sinaseyfi.remote.*
import com.sinaseyfi.remote.DESCRIPTION
import com.sinaseyfi.remote.HTML_URL
import com.sinaseyfi.remote.ID
import com.sinaseyfi.remote.NAME
import com.sinaseyfi.remote.PRIVATE
import com.sinaseyfi.remote.base.Dto

data class RepositoryDto(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(OWNER)
    val owner: RepositoryOwnerDto,
    @SerializedName(PRIVATE)
    val privat3: Boolean,
    @SerializedName(HTML_URL)
    val htmlUrl: String,
    @SerializedName(DESCRIPTION)
    val description: String
): Dto