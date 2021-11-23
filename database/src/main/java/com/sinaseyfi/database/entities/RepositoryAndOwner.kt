package com.sinaseyfi.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.sinaseyfi.database.ID
import com.sinaseyfi.database.REPO_ID
import com.sinaseyfi.database.Table

data class RepositoryAndOwner(
    @Embedded val repository: RepositoryTable,
    @Relation(parentColumn = ID, entityColumn = REPO_ID)
    val owner: OwnerTable?
): Table