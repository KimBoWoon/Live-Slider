package com.bowoon.android.live_slider.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.MajorNewsItemBinding
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.module.GlideApp
import com.bumptech.glide.Glide


class MajorNewsFragment : Fragment() {
    private lateinit var binding: MajorNewsItemBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<MajorNewsItemBinding>(
            inflater,
            R.layout.major_news_item,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val news = it.getSerializable("news") as Item

            binding.majorNewsTitle.text = news.title

            GlideApp
                .with(context!!)
                .load(news.ogTag.image)
                .centerCrop()
                .placeholder(R.mipmap.image_not_found)
                .into(binding.majorNewImage)
        }
    }
}