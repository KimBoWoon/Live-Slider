package com.bowoon.android.live_slider.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.NewsItemBinding
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.Glide

class AllNewsFragment : Fragment() {
    private lateinit var binding: NewsItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<NewsItemBinding>(
            inflater,
            R.layout.news_item,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val news = it.getSerializable("news") as Item

            binding.newsTitle.text = news.title

            Glide
                .with(context!!)
                .load(news.ogTag.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.newsImage)
        }
    }
}