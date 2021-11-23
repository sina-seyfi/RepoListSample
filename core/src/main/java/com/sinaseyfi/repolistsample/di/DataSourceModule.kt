package com.sinaseyfi.repolistsample.di

import com.sinaseyfi.data.sources.local.RepoLocalDataSource
import com.sinaseyfi.data.sources.remote.RepoRemoteDataSource
import com.sinaseyfi.database.sources.RepoDatabaseLocalDataSourceImpl
import com.sinaseyfi.remote.models.repo.RepoApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataSourceModule.Binder::class])
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class Binder {

        @Binds
        @Singleton
        abstract fun bindRepoApiService(repoApiService: RepoApiService): RepoRemoteDataSource

        @Binds
        @Singleton
        abstract fun bindRepoDatabaseImpl(
            repoDatabaseLocalDataSourceImpl: RepoDatabaseLocalDataSourceImpl
        ): RepoLocalDataSource

    }

}