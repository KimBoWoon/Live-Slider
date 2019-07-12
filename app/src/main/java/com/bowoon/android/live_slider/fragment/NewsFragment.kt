package com.bowoon.android.live_slider.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.Data
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.adapter.AdapterOfNews
import com.bowoon.android.live_slider.databinding.FragmentItemBinding
import com.bowoon.android.live_slider.type.NewsType
import com.bumptech.glide.Glide

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentItemBinding
    private lateinit var adapterOfNews: AdapterOfNews
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentItemBinding>(
            inflater,
            R.layout.fragment_item,
            container,
            false
        ).apply {
            adapterOfNews = AdapterOfNews(Glide.with(this@NewsFragment))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            when (it.getSerializable("type") as NewsType) {
                NewsType.ALL -> adapterOfNews.setItems(Data.allNews)
                NewsType.MONEY -> adapterOfNews.setItems(Data.moneyNews)
                NewsType.LIFE -> adapterOfNews.setItems(Data.lifeNews)
                NewsType.POLITICS -> adapterOfNews.setItems(Data.politicsNews)
                NewsType.WORLD -> adapterOfNews.setItems(Data.worldNews)
                NewsType.CULTURE -> adapterOfNews.setItems(Data.cultureNews)
                NewsType.IT -> adapterOfNews.setItems(Data.itNews)
                NewsType.DAILY -> adapterOfNews.setItems(Data.dailyNews)
                NewsType.SPORT -> adapterOfNews.setItems(Data.sportNews)
                NewsType.STAR -> adapterOfNews.setItems(Data.starNews)
                else -> adapterOfNews.setItems(Data.allNews)
            }
            binding.fragmentNewsView.layoutManager = layoutManager
            binding.fragmentNewsView.adapter = adapterOfNews
        }
    }
}