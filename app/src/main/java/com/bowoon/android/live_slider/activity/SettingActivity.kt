package com.bowoon.android.live_slider.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.data.SharedStorage
import com.bowoon.android.live_slider.data.model.NewsSetting
import com.bowoon.android.live_slider.data.type.NewsType
import com.bowoon.android.live_slider.databinding.SettingViewBinding
import com.bowoon.android.live_slider.log.Log
import org.json.JSONObject

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: SettingViewBinding
    private val newsSetting = NewsSetting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.setting_view)

        supportActionBar!!.title = "설정"

        binding.allNews.setOnClickListener(checkboxListener)
        binding.moneyNews.setOnClickListener(checkboxListener)
        binding.lifeNews.setOnClickListener(checkboxListener)
        binding.politicsNews.setOnClickListener(checkboxListener)
        binding.worldNews.setOnClickListener(checkboxListener)
        binding.cultureNews.setOnClickListener(checkboxListener)
        binding.itNews.setOnClickListener(checkboxListener)
        binding.dailyNews.setOnClickListener(checkboxListener)
        binding.sportNews.setOnClickListener(checkboxListener)
        binding.starNews.setOnClickListener(checkboxListener)
        binding.settingComplete.setOnClickListener(clickListener)
        binding.settingComplete.setOnClickListener(clickListener)
    }

    private val checkboxListener = object : View.OnClickListener {
        override fun onClick(p0: View?) {
            if (p0 is CheckBox) {
                val checked: Boolean = p0.isChecked

                when (p0.id) {
                    R.id.all_news -> {
                        if (checked) {
                            newsSetting.allNews = true
                        }
                    }
                    R.id.money_news -> {
                        if (checked) {
                            newsSetting.moneyNews = true
                        }
                    }
                    R.id.life_news -> {
                        if (checked) {
                            newsSetting.lifeNews = true
                        }
                    }
                    R.id.politics_news -> {
                        if (checked) {
                            newsSetting.politicsNews = true
                        }
                    }
                    R.id.world_news -> {
                        if (checked) {
                            newsSetting.worldNews = true
                        }
                    }
                    R.id.culture_news -> {
                        if (checked) {
                            newsSetting.cultureNews = true
                        }
                    }
                    R.id.it_news -> {
                        if (checked) {
                            newsSetting.itNews = true
                        }
                    }
                    R.id.daily_news -> {
                        if (checked) {
                            newsSetting.dailyNews = true
                        }
                    }
                    R.id.sport_news -> {
                        if (checked) {
                            newsSetting.sportNews = true
                        }
                    }
                    R.id.star_news -> {
                        if (checked) {
                            newsSetting.starNews = true
                        }
                    }
                }
            }
        }
    }

    private val clickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            if (v != null) {
                when (v.id) {
                    R.id.setting_complete -> {
                        SharedStorage.initSetting.put("newsSetting", newsSetting)
                        finish()
                    }
                    R.id.setting_cancel -> {
                        finish()
                    }
                }
            } else {
                throw NullPointerException("view is null")
            }
        }
    }
}