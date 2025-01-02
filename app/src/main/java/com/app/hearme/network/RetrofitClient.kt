package com.app.hearme.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL_PROD = "https://python-flask-sqlite.onrender.com/"
    private const val BASE_URL_UAT = "http://127.0.0.1:5000"

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val retrofitInstance: Retrofit by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL_PROD)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}