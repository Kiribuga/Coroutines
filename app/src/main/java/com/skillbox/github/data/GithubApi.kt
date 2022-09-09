package com.skillbox.github.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface GithubApi {
    @GET("user")
    suspend fun infoUser() : UserInfo.CurrentUser

    @GET("user/following")
    suspend fun infoFollow() : List<UserInfo.UserFollow>

    @GET("repositories")
    suspend fun reposUser() : List<UserInfo.ReposUser>

    @GET("user/starred/{owner}/{repo}")
    fun infoStarred(
        @Path("owner") ownerName: String,
        @Path("repo") repository: String
    ) : Call<Boolean>

    @PUT("user/starred/{owner}/{repo}")
    suspend fun putStarred(
        @Path("owner") ownerName: String,
        @Path("repo") repository: String
    ) : Response<Unit>

    @DELETE("user/starred/{owner}/{repo}")
    suspend fun delStarred(
        @Path("owner") ownerName: String,
        @Path("repo") repository: String
    ) : Response<Unit>
}