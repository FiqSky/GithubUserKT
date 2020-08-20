package com.fiqsky.githubuserapp.utils

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("login")
    val userName: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("followers_url")
    val followersUrl: String = "",
    @SerializedName("following_url")
    val followingUrl: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("location")
    val location: String = "",
    @SerializedName("followers")
    val totalFollowers: Int = 0,
    @SerializedName("following")
    val totalFollowing: Int = 0,
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
    @SerializedName("company")
    val company: String = "",
    val blog: String = "",
    val repository: String = "",
    val followersCount: String = "",
    val followingCount: String = "",
    val avatar: Int = -1
) : Parcelable

data class SearchResponse(
    @SerializedName("items")
    val items: List<User> = mutableListOf()
)