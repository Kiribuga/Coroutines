package com.skillbox.github.data

import net.openid.appauth.ResponseTypeValues

object AuthConfig {

    const val AUTH_URI = "https://github.com/login/oauth/authorize"
    const val TOKEN_URI = "https://github.com/login/oauth/access_token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE = "user,repo"

    const val CLIENT_ID = "a16919f3a228fcbdb6fd"
    const val CLIENT_SECRET = "f9fe6fded60b8a9500a8c2afad85df26d1b5e012"
    const val CALLBACK_URL = "skillbox://skillbox.ru/callback"
}