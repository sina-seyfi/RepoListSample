package com.sinaseyfi.remote.models.repo

import com.sinaseyfi.remote.base.Dto

interface BaseUser: Dto {
    val id: Long
    val avatarUrl: String
    val login: String
    val htmlUrl: String
}