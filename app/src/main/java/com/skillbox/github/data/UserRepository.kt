package com.skillbox.github.data

import android.util.Log
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepository {

    suspend fun asyncInfoFun(): List<UserInfo> {
        val errorHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d("Repository", "Error response", throwable)
        }
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.IO + errorHandler).launch {
                val deferredUser = async { infoUser() }
                val deferredFollow = async { infoFollowUser() }
                continuation.resume(deferredUser.await() + deferredFollow.await())
            }
        }
    }

    private suspend fun infoUser(): List<UserInfo.CurrentUser> {
        val response = Network.githubApi.infoUser()
        return listOf(response)
    }

    private suspend fun infoFollowUser(): List<UserInfo.UserFollow> {
        return Network.githubApi.infoFollow()
    }

    suspend fun reposInfo(): List<UserInfo.ReposUser> {
        return withContext(Dispatchers.IO) {
            Network.githubApi.reposUser()
        }
    }

    suspend fun infoStarred(
        nameUser: String,
        nameRepo: String
    ): Boolean {
        return suspendCancellableCoroutine { continuation ->
            continuation.invokeOnCancellation {
                continuation.cancel()
            }
            Network.githubApi.infoStarred(nameUser, nameRepo).enqueue(
                object : Callback<Boolean> {
                    override fun onFailure(call: Call<Boolean>, t: Throwable) {
                        continuation.resumeWithException(t)
                    }

                    override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                        when {
                            response.code() == 204 -> {
                                continuation.resume(true)
                            }
                            response.code() == 404 -> {
                                continuation.resume(false)
                            }
                            else -> {
                                continuation.resumeWithException(RuntimeException("incorrect status code"))
                            }
                        }
                    }
                }
            )
        }
    }

    suspend fun putStar(
        nameUser: String,
        nameRepo: String
    ): Boolean {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = Network.githubApi.putStarred(nameUser, nameRepo)
                    when {
                        response.code() == 204 -> {
                            continuation.resume(true)
                        }
                        response.code() == 304 -> {
                            continuation.resume(false)
                        }
                        else -> {
                            continuation.resumeWithException(RuntimeException("incorrect status code"))
                        }
                    }
                } catch (t: Throwable) {
                    continuation.resumeWithException(RuntimeException("Error response", t))
                }
            }
        }
    }

    suspend fun delStar(
        nameUser: String,
        nameRepo: String
    ): Boolean {
        return suspendCoroutine { continuation ->
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = Network.githubApi.delStarred(nameUser, nameRepo)
                    when {
                        response.code() == 204 -> {
                            continuation.resume(true)
                        }
                        response.code() == 304 -> {
                            continuation.resume(false)
                        }
                        else -> {
                            continuation.resumeWithException(RuntimeException("incorrect status code"))
                        }
                    }
                } catch (t: Throwable) {
                    continuation.resumeWithException(RuntimeException("Error response", t))
                }
            }
        }
    }
}