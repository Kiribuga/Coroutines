package com.skillbox.github.data

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Network {

    private val okhttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            CustomInterceptor(
                "Authorization",
                "token ${AccessToken.accessToken}")
        )
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val moshi = Moshi.Builder()
        .add(CustomAdapterRepo())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okhttpClient)
        .build()

    val githubApi: GithubApi
        get() = retrofit.create()
}