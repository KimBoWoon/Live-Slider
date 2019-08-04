package com.bowoon.android.live_slider.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.data.DataRepository
import com.bowoon.android.live_slider.databinding.NewsItemBinding
import com.bowoon.android.live_slider.listener.ItemClickListener
import com.bowoon.android.live_slider.data.model.Item
import com.bowoon.android.live_slider.data.type.NewsType
import com.bumptech.glide.RequestManager


class AdapterOfNews(
    private val itemClicked: ItemClickListener,
    private val type: NewsType,
    requestManager: RequestManager
) :
    RecyclerView.Adapter<AdapterOfNews.Companion.NewsItemHolder>() {
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
        holder.binding.newsDescription.text = items!![position].description
        holder.binding.callback = itemClicked
        holder.binding.item = items!![position]
        glide
            .load(items!![position].ogTag.image)
            .centerCrop()
            .placeholder(R.mipmap.image_not_found)
            .into(holder.binding.newsImage)

        if (position == itemCount - 1) {
            if (position + 5 < DataRepository.allNews.value!!.channel.item.size) {
                EndlessScrollListener.onLoadMore(position + 1, type, items!!)
            } else {
                EndlessScrollListener.onLoadMore(position + 1, DataRepository.allNews.value!!.channel.item.size, type, items!!)
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