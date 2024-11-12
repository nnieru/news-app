package com.example.newsapp.data.repositories

import com.example.newsapp.data.dto.NewsResponseDTO
import com.example.newsapp.data.source.api.NewsService
import com.example.newsapp.domain.entities.Article
import com.example.newsapp.domain.entities.NewsResponse
import com.example.newsapp.domain.entities.Source
import com.example.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(val newsService: NewsService): NewsRepository {

    override suspend fun getTOpHeadlines(category: String, apiKey: String): NewsResponse {
        val responseDto = newsService.getTopHeadlines(category, apiKey)
        return  responseDto.toNews()
    }


    // Mapping
    private fun NewsResponseDTO.toNews(): NewsResponse {
        return NewsResponse(
            status = status,
            totalResults = totalResults,
            articles =  articles.map { it.toArticle() }
        )
    }

    private  fun NewsResponseDTO.ArticleDTO.toArticle(): Article {
        return Article(
            source = source.toSource(),
            author = author,
            title = title,
            description = description,
            articleUrl = url,
            imageUrl = urlToImage,
            publishedAt = publishedAt,
            content = content
        )
    }

    private fun NewsResponseDTO.ArticleDTO.SourceDTO.toSource(): Source {
        return Source(
            id = id,
            name = name
        )
    }
}