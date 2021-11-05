package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.materialsouk.allcodeapp.googleAds.BannerAdsActivity
import com.materialsouk.allcodeapp.googleAds.InterstitialAdsActivity

class GoogleAdsActivity : AppCompatActivity() {
    private var interstitialAd: InterstitialAd? = null
    private var mRewardedAd: RewardedAd? = null
    private var totalRewardAmount = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_ads)
        MobileAds.initialize(this) {}
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        totalRewardAmount = sharedPreferences.getInt("totalRewardAmount", 0)
        findViewById<TextView>(R.id.totalRewardedAmountText).text = "Total Reward Coins : $totalRewardAmount Coins"
        findViewById<Button>(R.id.banner_adsBtn).setOnClickListener {
            startActivity(Intent(this, BannerAdsActivity::class.java))
        }
        //    Todo: interstitial Ads
        loadInterstitialAds()
        findViewById<Button>(R.id.interstitial_adsBtn).setOnClickListener {
            showInterstitial()
        }
        //    Todo: interstitial Ads

        //    Todo: Rewarded Ads
        loadRewardedAds()
        findViewById<Button>(R.id.rewarded_adsBtn).setOnClickListener {
            showRewardedAds()
        }
        //    Todo: Rewarded Ads
    }

    //    Todo: interstitial Ads
    private fun loadInterstitialAds() {
        val adRequest: AdRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            getString(R.string.interstitialAdsId),
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(mInterstitialAd: InterstitialAd) {
                    interstitialAd = mInterstitialAd
                    mInterstitialAd.fullScreenContentCallback =
                        object : FullScreenContentCallback() {
                            override fun onAdDismissedFullScreenContent() {
                                interstitialAd = null
                                codeInterstitialIntent()
                            }

                            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                                interstitialAd = null
                                codeInterstitialIntent()
                            }

                            override fun onAdShowedFullScreenContent() {}
                        }
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    // Handle the error
                    interstitialAd = null
                }
            })
    }

    private fun showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null) {
            interstitialAd!!.show(this)
        } else {
            codeInterstitialIntent()
        }
    }

    private fun codeInterstitialIntent() {
        startActivity(Intent(this, InterstitialAdsActivity::class.java))
    }
    //    Todo: interstitial Ads

    //    Todo: Rewarded Ads
    private fun loadRewardedAds() {
        val adRequest = AdRequest.Builder().build()

        RewardedAd.load(
            this,
            getString(R.string.rewardedAdsId),
            adRequest,
            object : RewardedAdLoadCallback() {

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("TAG", adError.message)
                    mRewardedAd = null
                }

                override fun onAdLoaded(rewardedAd: RewardedAd) {
                    Log.d("TAG", "Ad was loaded.")
                    mRewardedAd = rewardedAd
                }
            })
    }

    @SuppressLint("SetTextI18n")
    private fun showRewardedAds() {
        if (mRewardedAd != null) {
            mRewardedAd?.fullScreenContentCallback = object : FullScreenContentCallback() {

                override fun onAdDismissedFullScreenContent() {
                    Log.d("TAG", "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mRewardedAd = null
                    loadRewardedAds()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("TAG", "Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mRewardedAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("TAG", "Ad showed fullscreen content.")
                    // Called when ad is dismissed.

                }
            }
            mRewardedAd?.show(this) {
                val rewardAmount = it.amount
                Log.d("rewardAmount", rewardAmount.toString())
                totalRewardAmount += rewardAmount
                val sharedPreferences =
                    getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
                sharedPreferences.edit().putInt("totalRewardAmount", totalRewardAmount)
                    .apply()
                Log.d("TAG", rewardAmount.toString())
                findViewById<TextView>(R.id.totalRewardedAmountText).text =
                    "Total Reward Coins : $totalRewardAmount Coins"
            }


        } else {
            Toast.makeText(this, "Ads is not loaded", Toast.LENGTH_LONG).show()
        }
    }
    //    Todo: Rewarded Ads

}