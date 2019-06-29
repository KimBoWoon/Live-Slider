package com.bowoon.android.live_slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.model.Channel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val r = RetrofitClass()
        r.getRSS(object : HttpCallback {
            override fun onSuccess(o: Any) {
                if (o is Channel) {
                    for (i in o.item) {
                        binding.mainText.text = binding.mainText.text.toString() + i.title + "\n"
                    }
                }
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
