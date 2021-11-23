package com.sinaseyfi.remote.models.repo

import com.google.gson.annotations.SerializedName
import com.sinaseyfi.remote.AVATAR_URL
import com.sinaseyfi.remote.HTML_URL
import com.sinaseyfi.remote.ID
import com.sinaseyfi.remote.LOGIN

data class RepositoryOwnerDto(
    @SerializedName(ID)
    override val id: Long,
    @SerializedName(AVATAR_URL)
    override val avatarUrl: String,
    @SerializedName(LOGIN)
    override val login: String,
    @SerializedName(HTML_URL)
    override val htmlUrl: String
): BaseUser