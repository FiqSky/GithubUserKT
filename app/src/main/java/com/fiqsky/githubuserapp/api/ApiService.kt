package com.fiqsky.githubuserapp.api

import com.fiqsky.githubuserapp.BuildConfig
import com.fiqsky.githubuserapp.SearchResponse
import com.fiqsky.githubuserapp.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers(BuildConfig.TOKEN)
    fun getSearchResult(
        @Query("q") q: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") username: String
    ): Call<User>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<User>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<User>>
}
