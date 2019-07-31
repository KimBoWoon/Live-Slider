package com.bowoon.android.live_slider.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.adapter.AdapterOfNews
import com.bowoon.android.live_slider.databinding.NewsItemViewBinding
import com.bowoon.android.live_slider.model.Rss
import com.bowoon.android.live_slider.module.GlideApp
import com.bowoon.android.live_slider.type.NewsType
import com.bowoon.android.live_slider.viewmodel.DataViewModel

class NewsFragment : Fragment() {
    private lateinit var binding: NewsItemViewBinding
    private lateinit var adapterOfNews: AdapterOfNews
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var newsViewModel: DataViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<NewsItemViewBinding>(
            inflater,
            R.layout.news_item_view,
            container,
            false
        ).apply {
            adapterOfNews = AdapterOfNews(GlideApp.with(this@NewsFragment))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            initViewModel(it.getSerializable("type") as NewsType)
            binding.fragmentNewsView.layoutManager = layoutManager
            binding.fragmentNewsView.adapter = adapterOfNews
        }
    }

    private fun initViewModel(type: NewsType) {
        newsViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        newsViewModel.getRSS(type).observe(this, object : Observer<Rss> {
            override fun onChanged(t: Rss?) {
                t?.let {
                    adapterOfNews.setItems(it.channel.item)
                }
            }
        })
    }
}