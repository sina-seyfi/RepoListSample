package com.sinaseyfi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sinaseyfi.database.daos.RepositoryDao
import com.sinaseyfi.database.entities.OwnerTable
import com.sinaseyfi.database.entities.RepositoryTable


@Database(
    version = BuildConfig.DATABASE_VERSION,
    entities = [RepositoryTable::class, OwnerTable::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepositoryDao(): RepositoryDao

}