package com.bowoon.android.live_slider.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.activity.WebViewActivity
import com.bowoon.android.live_slider.data.model.Item
import com.bowoon.android.live_slider.databinding.MajorNewsItemBinding
import com.bowoon.android.live_slider.listener.ItemClickListener
import com.bowoon.android.live_slider.module.GlideApp


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

            binding.callback = itemClicked
            binding.item = news
        }
    }

    private val itemClicked = object : ItemClickListener {
        override fun onClick(item: Item) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }
    }
}