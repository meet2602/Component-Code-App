package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.viewpager.widget.ViewPager
import com.materialsouk.allcodeapp.adapters.ImageUsPagerAdapter



class ImageSliderUsViewPagerActivity : AppCompatActivity() {
    private val images = intArrayOf(
        R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
        R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider_us_view_pager)

        val mViewPager = findViewById<ViewPager>(R.id.viewPager)

        val mViewPagerAdapter = ImageUsPagerAdapter(this, images)
        mViewPager.adapter = mViewPagerAdapter
    }

}