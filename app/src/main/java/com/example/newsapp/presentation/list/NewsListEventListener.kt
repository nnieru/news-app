package com.example.newsapp.presentation.list

import com.example.newsapp.domain.entities.Article

interface NewsListEventListener {
    fun onItemClicker(article: Article)
}