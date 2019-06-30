package com.bowoon.android.live_slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.adapter.NewsItemAdapter
import com.bowoon.android.live_slider.databinding.FragmentImageBinding
import com.bowoon.android.live_slider.databinding.MainNewsItemBinding
import com.bowoon.android.live_slider.databinding.NewsItemBinding
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Item


class MainNewsFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<MainNewsItemBinding>(
            inflater,
            R.layout.main_news_item,
            container,
            false
        )

        binding.mainNewsTitle.text = "Hello, World!"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}