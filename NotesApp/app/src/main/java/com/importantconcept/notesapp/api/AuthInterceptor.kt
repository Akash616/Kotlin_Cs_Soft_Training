package com.importantconcept.notesapp.api

import com.importantconcept.notesapp.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        //Header Add
        val token = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer $token") //API requirement-> Bearer

        return chain.proceed(request.build())
    }

}