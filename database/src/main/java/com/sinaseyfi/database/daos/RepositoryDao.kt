package com.sinaseyfi.database.daos

import androidx.paging.PagingSource
import androidx.room.*
import com.sinaseyfi.database.entities.OwnerTable
import com.sinaseyfi.database.entities.RepositoryAndOwner
import com.sinaseyfi.database.entities.RepositoryTable
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RepositoryDao {

    @Query("select * from repository")
    abstract fun fetchRepositoryList(): Flow<List<RepositoryAndOwner>>

    @Query("select * from repository where id > :since")
    abstract fun fetchRepositoryList(since: Long): List<RepositoryAndOwner>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = RepositoryTable::class)
    abstract suspend fun cacheRepositoryList(list: List<RepositoryTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = OwnerTable::class)
    abstract suspend fun cacheOwnerList(list: List<OwnerTable>)

    @Query("delete from repository")
    abstract suspend fun clearRepositoryList()

    @Transaction
    open suspend fun cacheRepositoryAndOwnerList(repoList: List<RepositoryTable>, ownerList: List<OwnerTable>) {
        cacheRepositoryList(repoList)
        cacheOwnerList(ownerList)
    }

}