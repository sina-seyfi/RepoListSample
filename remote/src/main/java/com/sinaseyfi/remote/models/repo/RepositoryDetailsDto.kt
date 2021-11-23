package com.sinaseyfi.remote.models.repo

import com.google.gson.annotations.SerializedName
import com.sinaseyfi.remote.*
import com.sinaseyfi.remote.DESCRIPTION
import com.sinaseyfi.remote.FORKS
import com.sinaseyfi.remote.ID
import com.sinaseyfi.remote.NAME
import com.sinaseyfi.remote.STARGAZERS_COUNT
import com.sinaseyfi.remote.base.Dto

data class RepositoryDetailsDto(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(STARGAZERS_COUNT)
    val stargazersCount: Int,
    @SerializedName(FORKS)
    val forks: Int,
    @SerializedName(DESCRIPTION)
    val description: String,
    @SerializedName(HTML_URL)
    val htmlUrl: String,
    @SerializedName(OWNER)
    val owner: RepositoryOwnerDto,
    @SerializedName(PRIVATE)
    val private: Boolean
): Dto
