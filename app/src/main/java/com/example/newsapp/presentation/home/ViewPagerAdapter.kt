package com.example.newsapp.presentation.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.presentation.list.ListFragment

class ViewPagerAdapter (fragment: Fragment): FragmentStateAdapter(fragment) {

    val categoryList = listOf("business",
        "entertainment",
        "general",
        "health",
        "science",
        "sports",
        "technology")
    override fun getItemCount(): Int {
        return  categoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        return ListFragment.newInstance(categoryList[position])
    }
}