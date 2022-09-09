package com.skillbox.github.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class UserInfo {

    @JsonClass(generateAdapter = true)
    data class CurrentUser(
        @Json(name = "id")
        val id: Long,
        @Json(name = "login")
        val name: String,
        @Json(name = "avatar_url")
        val avatar: String,
        @Json(name = "type")
        val typeUser: String,
        @Json(name = "public_repos")
        val quantityRepo: Int
    ): UserInfo()

    @JsonClass(generateAdapter = true)
    data class ReposUser(
        @Json(name = "id")
        val idRepo: Long,
        @Json(name = "name")
        val nameRepo: String,
        @Json(name = "owner")
        val userName: String
    ): UserInfo()

    @JsonClass(generateAdapter = true)
    data class UserFollow(
        @Json(name = "login")
        val nameFollow: String,
        @Json(name = "id")
        val idFollow: Long,
        @Json(name = "avatar_url")
        val avatarFollow: String
    ): UserInfo()
}
