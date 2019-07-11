package com.bowoon.android.live_slider.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bowoon.android.live_slider.fragment.NewsFragment
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.type.NewsType

class NewsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private var items: ArrayList<Item>? = null

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.ALL
                )
            }
            1 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.MONEY
                )
            }
            2 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.LIFE
                )
            }
            3 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.POLITICS
                )
            }
            4 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.WORLD
                )
            }
            5 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.CULTURE
                )
            }
            6 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.IT
                )
            }
            7 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.DAILY
                )
            }
            8 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.SPORT
                )
            }
            9 -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.STAR
                )
            }
            else -> NewsFragment().apply {
                arguments = bundleOf(
                    "type" to NewsType.ALL
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (items == null) {
            0
        } else {
            items!!.size
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
}