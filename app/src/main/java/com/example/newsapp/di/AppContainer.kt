package com.example.newsapp.di

import com.example.newsapp.AppViewModelFactory
import com.example.newsapp.data.repositories.NewsRepositoryImpl
import com.example.newsapp.data.source.api.NewsService
import com.example.newsapp.data.source.api.RetrofitClient
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.GetTopicHeadlinesUseCase
import com.example.newsapp.presentation.list.NewsListViewModel

class AppContainer {
    private val retrofit = RetrofitClient.retrofit

    private val newsService: NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }

    val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(newsService)
    }

    val getTopicHeadlinesUseCase by lazy {
        GetTopicHeadlinesUseCase(newsRepository)
    }

    fun provideViewModelFactory(): AppViewModelFactory {
        return  AppViewModelFactory().apply {
            // Register viewModel
            registerCreator(NewsListViewModel::class.java) {NewsListViewModel(getTopicHeadlinesUseCase)}
        }
    }
}