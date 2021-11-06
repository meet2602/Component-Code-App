package com.materialsouk.allcodeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.materialsouk.allcodeapp.adapters.ImageUsPagerAdapter

class ReceivingDataFromOtherAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiving_data_from_other_app)

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    handleSendText(intent) // Handle text being sent
                } else if (intent.type?.startsWith("image/") == true) {
                    handleSendImage(intent) // Handle single image being sent
                }
            }
            intent?.action == Intent.ACTION_SEND_MULTIPLE
                    && intent.type?.startsWith("image/") == true -> {
                handleSendMultipleImages(intent) // Handle multiple images being sent
            }
        }
    }
    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            findViewById<TextView>(R.id.receivingDataTxt).text = it
        }
    }

    private fun handleSendImage(intent: Intent) {
        (intent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as? Uri)?.let {
            // Update UI to reflect image being shared
            findViewById<ImageView>(R.id.receivingSingleImageView).setImageURI(it)
        }
    }

    private fun handleSendMultipleImages(intent: Intent) {
        intent.getParcelableArrayListExtra<Parcelable>(Intent.EXTRA_STREAM)?.let {
            // Update UI to reflect multiple images being shared
            val mViewPager = findViewById<ViewPager>(R.id.viewPager)
            val imageArrayList : ArrayList<String> = ArrayList()
            for (i in it){
                imageArrayList.add(i.toString())
            }
            val mViewPagerAdapter = ImageUsPagerAdapter(false, this, null, imageArrayList)
            mViewPager.adapter = mViewPagerAdapter
        }
    }
}