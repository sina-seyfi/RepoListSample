package com.sinaseyfi.data.sources.local

import androidx.paging.PagingSource
import com.sinaseyfi.domain.models.RepositoryDataModel
import kotlinx.coroutines.flow.Flow

interface RepoLocalDataSource {

    companion object {
        const val DATABASE_PAGE_SIZE = 20
    }

    fun fetchRepositoryList(): Flow<List<RepositoryDataModel>>
    suspend fun cacheRepositoryList(repos: List<RepositoryDataModel>)
    suspend fun clearRepositoryList()


    fun fetchRepositoryPaging(): PagingSource<Long, RepositoryDataModel>

}