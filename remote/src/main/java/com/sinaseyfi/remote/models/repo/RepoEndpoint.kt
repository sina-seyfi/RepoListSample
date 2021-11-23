package com.sinaseyfi.remote.models.repo

import com.sinaseyfi.remote.*
import retrofit2.Response
import retrofit2.http.*

interface RepoEndpoint {

    @GET("repositories")
    suspend fun fetchRepositories(@Query(SINCE) since: Long): Response<List<RepositoryDto>>

    @GET("repos/{${OWNER}}/{${REPO}}")
    suspend fun fetchRepository(@Path(OWNER) ownerName: String, @Path(REPO) repoName: String): Response<RepositoryDetailsDto>

    @GET("repos/{${OWNER}}/{${REPO}}/collaborators")
    suspend fun fetchCollaborators(
        @Path(OWNER) ownerName: String,
        @Path(REPO) repoName: String,
        @Query(AFFILIATION) affiliation: String,    // We only want public collaborators.
        @Query(PER_PAGE) perPage: Int,
        @Query(PAGE) page: Int
    ): Response<List<RepositoryCollaboratorDto>>

}