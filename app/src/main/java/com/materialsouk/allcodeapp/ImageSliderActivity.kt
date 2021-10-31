package com.materialsouk.allcodeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ImageSliderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)
        findViewById<Button>(R.id.imageSliderUsViewPagerBtn).setOnClickListener {
            startActivity(Intent(this, ImageSliderUsViewPagerActivity::class.java))
        }
    }
}