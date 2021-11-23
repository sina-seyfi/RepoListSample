package com.sinaseyfi.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.sinaseyfi.database.*
import com.sinaseyfi.database.DESCRIPTION
import com.sinaseyfi.database.HTML_URL
import com.sinaseyfi.database.NAME
import com.sinaseyfi.database.PRIVATE

@Entity(tableName = "repository")
data class RepositoryTable(
    @ColumnInfo(name = ID)
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    @ColumnInfo(name = NAME, defaultValue = "")
    val repoName: String,
    @ColumnInfo(name = PRIVATE, defaultValue = "false")
    val privat3: Boolean, // Don't you dare and try to name it "private". Project will not be build!!!
    @ColumnInfo(name = HTML_URL, defaultValue = "")
    val htmlUrl: String,
    @ColumnInfo(name = DESCRIPTION, defaultValue = "")
    val description: String
): Table