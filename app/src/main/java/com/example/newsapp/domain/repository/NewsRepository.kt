package com.example.newsapp.domain.repository

import com.example.newsapp.domain.entities.NewsResponse

interface NewsRepository {
    suspend fun getTOpHeadlines(category: String, apiKey: String): NewsResponse
}