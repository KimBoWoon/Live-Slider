package com.bowoon.android.live_slider.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.BasicApp
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.NewsItemBinding
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.RequestManager

class NewsItemAdapter(requestManager: RequestManager) :
    RecyclerView.Adapter<NewsItemAdapter.Companion.NewsItemHolder>() {
    private var items: List<Item>? = null
    private val glide = requestManager
    private val endlessScrollListener = EndlessScrollListener()

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
            endlessScrollListener.onLoadMore(position)
            notifyDataSetChanged()
        }
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setItems(items: List<Item>, idx: Int) {
        this.items = items
        notifyItemChanged(idx)
    }

    companion object {
        class NewsItemHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
    }

//    inner class EndlessScrollListener : RecyclerView.OnScrollListener() {
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            super.onScrolled(recyclerView, dx, dy)
//        }
//
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            super.onScrollStateChanged(recyclerView, newState)
//        }
//
//        fun onLoadMore(position: Int) {
//            items!!.addAll(BasicApp.newsItems.subList(position, position + 5))
//        }
//    }
}