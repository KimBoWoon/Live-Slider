package com.bowoon.android.live_slider.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.databinding.SettingViewBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: SettingViewBinding
    private var result = ""

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
    }

    private val checkboxListener = object : View.OnClickListener {
        override fun onClick(p0: View?) {
            when {
                binding.allNews.isChecked -> result += "allNewsChecked"
                binding.moneyNews.isChecked -> result += "moneyNewsChecked"
                binding.lifeNews.isChecked -> result += "lifeNewsChecked"
                binding.politicsNews.isChecked -> result += "politicsNewsChecked"
                binding.worldNews.isChecked -> result += "worldNewsChecked"
                binding.cultureNews.isChecked -> result += "cultureNewsChecked"
                binding.itNews.isChecked -> result += "itNewsChecked"
                binding.dailyNews.isChecked -> result += "dailyNewsChecked"
                binding.sportNews.isChecked -> result += "sportNewsChecked"
                binding.starNews.isChecked -> result += "starNewsChecked"
            }

            Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()

            val resultIntent = Intent()
            resultIntent.putExtra("result", result)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}