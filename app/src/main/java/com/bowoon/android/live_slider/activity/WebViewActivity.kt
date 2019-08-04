package com.bowoon.android.live_slider.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.NewsViewBinding
import com.bowoon.android.live_slider.data.model.Item

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: NewsViewBinding
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.news_view)

        item = intent.getSerializableExtra("item") as Item

        supportActionBar?.title = item.title

        binding.newsWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.newsLoadingProgress.visibility = View.GONE
            }
        }
//        binding.newsWebView.settings.javaScriptEnabled = true
        binding.newsWebView.loadUrl(item.link)
    }
}