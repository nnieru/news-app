package com.example.newsapp.presentation.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.entities.NewsResponse
import com.example.newsapp.domain.usecases.GetTopicHeadlinesUseCase
import kotlinx.coroutines.launch

class NewsListViewModel(private  val getTopicHeadlinesUseCase: GetTopicHeadlinesUseCase):ViewModel() {

    private val _newsList = MutableLiveData<NewsResponse>()
    val newsList = _newsList

    private val apiKey = "cd3586e5abb64c5497ba24fb530f8960"

    fun fetchTopHeadlines(category: String) {
        viewModelScope.launch {
            try {
                val newsResponse = getTopicHeadlinesUseCase.invoke(category, apiKey)
                _newsList.value = newsResponse
                Log.d("NewsListViewModel", "fetchTopHeadlines called with category: $category")


            } catch (e: Exception) {
                println(e.message)
                Log.e("error",e.message ?: "")
            }
        }
    }
}