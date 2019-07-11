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
import com.bowoon.android.live_slider.adapter.NewsItemAdapter
import com.bowoon.android.live_slider.databinding.FragmentItemBinding
import com.bowoon.android.live_slider.type.NewsType
import com.bumptech.glide.Glide

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentItemBinding
    private lateinit var adapter: NewsItemAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentItemBinding>(
            inflater,
            R.layout.fragment_item,
            container,
            false
        ).apply {
            adapter = NewsItemAdapter(Glide.with(this@NewsFragment))
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            when (it.getSerializable("type") as NewsType) {
                NewsType.ALL -> adapter.setItems(Data.allNews)
                NewsType.MONEY -> adapter.setItems(Data.moneyNews)
                NewsType.LIFE -> adapter.setItems(Data.lifeNews)
                NewsType.POLITICS -> adapter.setItems(Data.politicsNews)
                NewsType.WORLD -> adapter.setItems(Data.worldNews)
                NewsType.CULTURE -> adapter.setItems(Data.cultureNews)
                NewsType.IT -> adapter.setItems(Data.itNews)
                NewsType.DAILY -> adapter.setItems(Data.dailyNews)
                NewsType.SPORT -> adapter.setItems(Data.sportNews)
                NewsType.STAR -> adapter.setItems(Data.starNews)
                else -> adapter.setItems(Data.allNews)
            }
            binding.fragmentRecyclerView.layoutManager = layoutManager
            binding.fragmentRecyclerView.adapter = adapter
        }
    }
}