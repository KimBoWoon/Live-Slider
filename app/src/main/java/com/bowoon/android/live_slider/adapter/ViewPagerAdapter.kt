package com.bowoon.android.live_slider.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bowoon.android.live_slider.fragment.MainNewsFragment
import com.bowoon.android.live_slider.model.Item

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private var items: ArrayList<Item>? = null

    override fun createFragment(position: Int): Fragment = MainNewsFragment().apply {
        arguments = bundleOf(
            "news" to items!![position]
        )
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