package com.example.newsapp.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapp.domain.entities.Article

class DiffUtilCallback: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return  oldItem == newItem
    }

}