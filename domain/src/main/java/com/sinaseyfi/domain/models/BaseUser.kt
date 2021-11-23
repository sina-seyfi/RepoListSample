package com.sinaseyfi.domain.models

import com.sinaseyfi.domain.base.DataModel

interface BaseUser: DataModel {
    val id: Long
    val avatarUrl: String
    val login: String
    val htmlUrl: String
}