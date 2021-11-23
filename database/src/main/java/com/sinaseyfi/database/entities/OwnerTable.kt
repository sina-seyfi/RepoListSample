package com.sinaseyfi.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.sinaseyfi.database.*

@Entity(tableName = "owner")
data class OwnerTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,
    @ColumnInfo(name = OWNER_ID)
    val ownerId: Long,
    @ColumnInfo(name = REPO_ID)
    val repoId: Long,
    @ColumnInfo(name = AVATAR_URL)
    val avatarUrl: String,
    @ColumnInfo(name = LOGIN)
    val login: String,
    @ColumnInfo(name = HTML_URL)
    val htmlUrl: String
): Table