package com.bowoon.android.live_slider.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.MainNewsItemBinding
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.model.OGTag
import com.bumptech.glide.Glide


class MainNewsFragment : Fragment() {
    private lateinit var binding: MainNewsItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<MainNewsItemBinding>(
            inflater,
            R.layout.main_news_item,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val news = it.getSerializable("news") as Item

            binding.mainNewsTitle.text = news.title

            Glide
                .with(context!!)
                .load(news.ogTag.image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.mainNewsImage)
        }
    }
}