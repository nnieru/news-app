package com.example.newsapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AppViewModelFactory: ViewModelProvider.Factory {

    private val creators = mutableMapOf<Class<*>, ()->ViewModel>()

    fun <T: ViewModel> registerCreator(modelClass: Class<T>, creator: () -> T) {
        creators[modelClass] = creator
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw  IllegalArgumentException("Unknown ViewModel class")

        @Suppress("UNCHECKED_CAST") return creator() as T
    }

}