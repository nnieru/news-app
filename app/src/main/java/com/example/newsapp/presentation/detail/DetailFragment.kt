package com.example.newsapp.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.domain.entities.Article

class DetailFragment : Fragment() {

    private  lateinit var article: Article

    // view reference
    private val tvNewsTitle by lazy { view?.findViewById<TextView>(R.id.tv_news_title) }
    private val tvPublishedDate by lazy { view?.findViewById<TextView>(R.id.tv_news_date) }
    private val ivNewsImage by lazy { view?.findViewById<ImageView>(R.id.iv_news_image) }
    private val tvNewsContent by lazy { view?.findViewById<TextView>(R.id.content) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getParcelable<Article>("Article") as Article
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNewsTitle?.text = article.title
        tvPublishedDate?.text = article.publishedAt
        tvNewsContent?.text = article.content

        if (article.imageUrl != null) {
            ivNewsImage?.let { Glide.with(this).load(article.imageUrl).into(it) }
        }
    }


}