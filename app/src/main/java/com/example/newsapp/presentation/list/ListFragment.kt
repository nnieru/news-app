package com.example.newsapp.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.MyApplication
import com.example.newsapp.R
import com.example.newsapp.domain.entities.Article

private  const val KEY_NEWS_CATEGORY = "category"

class ListFragment : Fragment(), NewsListEventListener {
    private var newsCategory: String? = null
    private lateinit var newsListViewModel: NewsListViewModel
    private val newsListAdapter = NewsListAdapter(this)
    private lateinit var rvNewsList: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsCategory = it.getString(KEY_NEWS_CATEGORY)
        }
        val appContainer = (requireActivity().application as MyApplication).appContainer
        appContainer?.let {
            newsListViewModel = it.provideViewModelFactory().create(NewsListViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeNewsList()
        newsListViewModel.fetchTopHeadlines(newsCategory ?: "business")
    }

    private fun observeNewsList() {
        newsListViewModel.newsList.observe(viewLifecycleOwner) { news ->
            newsListAdapter.submitList(news.articles)
        }
    }

    private fun initList() {
        view?.let {
            rvNewsList = it.findViewById(R.id.rv_news_list)
            rvNewsList.layoutManager = LinearLayoutManager(context)
            rvNewsList.adapter = newsListAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(category: String) = ListFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_NEWS_CATEGORY, category)
            }
        }
    }

    override fun onItemClicker(article: Article) {
        val bundle = bundleOf("Article" to article)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }


}