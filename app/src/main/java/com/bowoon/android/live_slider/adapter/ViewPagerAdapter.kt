package com.bowoon.android.live_slider.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bowoon.android.live_slider.ImageFragment

class ViewPagerAdapter(private val tabCount: Int, fragmentManager: FragmentManager) : FragmentStateAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ImageFragment()
            1 -> ImageFragment()
            2 -> ImageFragment()
            else -> ImageFragment()
        }
    }

    override fun getItemCount(): Int {
        return tabCount
    }
}