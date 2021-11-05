package com.materialsouk.allcodeapp.googleAds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.*
import com.materialsouk.allcodeapp.R

class BannerAdsActivity : AppCompatActivity() {
    private lateinit var bannerAdsView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ads)
        loadBannerAds()

    }

    //    Todo: banner Ads
    private fun loadBannerAds() {
        bannerAdsView = findViewById(R.id.banner_ads_View)
        val adRequest = AdRequest.Builder().build()
        bannerAdsView.loadAd(adRequest)
        bannerAdsView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                bannerAdsView.loadAd(adRequest)
            }

        }
    }

    public override fun onPause() {
        bannerAdsView.pause()
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        bannerAdsView.resume()
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        bannerAdsView.destroy()
        super.onDestroy()
    }
    //    Todo: banner Ads


}