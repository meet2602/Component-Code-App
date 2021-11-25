package com.materialsouk.allcodeapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class CheckInternetActivity : AppCompatActivity() {
    private lateinit var snack: Snackbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_internet)
        snack =
            Snackbar.make(
                findViewById<TextView>(R.id.rootView),
                getString(R.string.no_internet),
                Snackbar.LENGTH_INDEFINITE
            )
        snack.setAction("Setting") {
            startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
        }
        findViewById<Button>(R.id.tryAgainBtn).setOnClickListener{
            checkInternet()
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            } else {
                null
            }
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }

    override fun onResume() {
        checkInternet()
        super.onResume()
    }
    private fun checkInternet(){
        if (isOnline()) {
            findViewById<TextView>(R.id.txtInternet).text =
                getString(R.string.internet_is_available)
            if (snack.isShown){
                snack.dismiss()
            }
        } else {
            findViewById<TextView>(R.id.txtInternet).text =
                getString(R.string.no_internet)
            snack.show()
        }
    }
}