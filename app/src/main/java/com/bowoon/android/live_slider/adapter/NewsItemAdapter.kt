package com.bowoon.android.live_slider.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.NewsItemBinding
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class NewsItemAdapter(requestManager: RequestManager) : RecyclerView.Adapter<NewsItemAdapter.Companion.NewsItemHolder>() {
    private var items: ArrayList<Item>? = null
    private val glide = requestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemHolder {
        val binding = DataBindingUtil.inflate<NewsItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.news_item,
            parent,
            false
        )

        return NewsItemHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (items == null) {
            0
        } else {
            items!!.size
        }
    }

    override fun onBindViewHolder(holder: NewsItemHolder, position: Int) {
        holder.binding.newsTitle.text = items!![position].title
        holder.binding.newsTitle.setTextColor(Color.BLACK)
        glide
            .load(items!![position].ogTag.image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.binding.newsImage)
    }

    fun setItems(items: ArrayList<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        class NewsItemHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
    }
}