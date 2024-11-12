package com.example.newsapp.domain.usecases

import com.example.newsapp.domain.entities.NewsResponse
import com.example.newsapp.domain.repository.NewsRepository

class GetTopicHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(category: String, apiKey: String): NewsResponse {
        return newsRepository.getTOpHeadlines(category, apiKey)
    }
}