package com.example.newsapp.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.domain.entities.Article

class NewsListAdapter(private val listener: NewsListEventListener): ListAdapter<Article, NewsListAdapter.ArticleViewHolder>(DiffUtilCallback()) {

    inner class ArticleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            val newsParentView = itemView.findViewById<ConstraintLayout>(R.id.cl_news_parent)
            val newsImageView = itemView.findViewById<ImageView>(R.id.iv_news_image)
            val newsDateView = itemView.findViewById<TextView>(R.id.tv_news_date)
            val newsTitleView = itemView.findViewById<TextView>(R.id.tv_news_title)
            val newsDescriptionView = itemView.findViewById<TextView>(R.id.tv_news_description)

            if (article.imageUrl != null) {
                Glide.with(itemView).load(article.imageUrl).into(newsImageView)
            }

            newsDateView.text = article.publishedAt
            newsTitleView.text = article.title
            newsDescriptionView.text = article.description ?: ""

            newsParentView.setOnClickListener {
                listener.onItemClicker(article)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsListAdapter.ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_list, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.ArticleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}