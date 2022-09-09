package com.skillbox.github.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

class CustomAdapterRepo {

    @FromJson
    fun fromJson(repo: RepoWrapper): UserInfo.ReposUser {
        return UserInfo.ReposUser(
            userName = repo.userName.userName,
            idRepo = repo.idRepo,
            nameRepo = repo.nameRepo
        )
    }

    @JsonClass(generateAdapter = true)
    data class RepoWrapper(
        @Json(name = "id")
        val idRepo: Long,
        @Json(name = "name")
        val nameRepo: String,
        @Json(name = "owner")
        val userName: OwnerData
    )
}