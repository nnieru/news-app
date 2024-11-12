package com.example.newsapp.data.source.api

import com.example.newsapp.data.dto.NewsResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String
        ): NewsResponseDTO
}