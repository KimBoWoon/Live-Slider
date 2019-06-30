package com.bowoon.android.live_slider.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bowoon.android.live_slider.MainNewsFragment

class ViewPagerAdapter(private val tabCount: Int, fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment = MainNewsFragment()

    override fun getItemCount(): Int {
        return tabCount
    }
}