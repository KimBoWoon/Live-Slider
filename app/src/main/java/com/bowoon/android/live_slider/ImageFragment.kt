package com.bowoon.android.live_slider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.databinding.FragmentImageBinding


class ImageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentImageBinding>(
            inflater,
            R.layout.fragment_image,
            container,
            false
        )

        binding.fragmentImage.setImageResource(R.mipmap.ic_launcher)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}