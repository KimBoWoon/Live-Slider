package com.bowoon.android.live_slider.adapter

import android.graphics.Color
import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.BasicApp
import com.bowoon.android.live_slider.Data
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.NewsItemBinding
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.RequestManager


class NewsItemAdapter(requestManager: RequestManager) :
    RecyclerView.Adapter<NewsItemAdapter.Companion.NewsItemHolder>() {
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

        if (position == itemCount - 1) {
            if (position + 5 < Data.newsItems.size) {
                EndlessScrollListener.onNewsLoadMore(position + 1, items!!)
            } else {
                EndlessScrollListener.onNewsLoadMore(position + 1, Data.newsItems.size, items!!)
            }
            Handler().post(object : Runnable {
                override fun run() {
                    notifyItemRangeChanged(position + 1, 5)
                }
            })
        }
    }

    fun setItems(items: ArrayList<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setItems(items: ArrayList<Item>, idx: Int) {
        this.items = items
        notifyItemChanged(idx)
    }

    companion object {
        class NewsItemHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
    }
}