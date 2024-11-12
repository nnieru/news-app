package com.example.newsapp.data.source.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://newsapi.org"

    private val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

     val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okhttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}