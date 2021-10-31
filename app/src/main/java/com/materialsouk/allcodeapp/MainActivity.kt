package com.materialsouk.allcodeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        when (sharedPreferences.getInt("night_mode", 2)) {
            0 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        findViewById<Button>(R.id.permissionBtn).setOnClickListener {
            startActivity(Intent(this, PermissionActivity::class.java))
        }
        findViewById<Button>(R.id.themeBtn).setOnClickListener {
            startActivity(Intent(this, ThemeChangeActivity::class.java))
        }
        findViewById<Button>(R.id.animationBtn).setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
        findViewById<Button>(R.id.viewPageWithViewPagerBtn).setOnClickListener {
            startActivity(Intent(this, TabLayoutWithViewPagerActivity::class.java))
        }
        findViewById<Button>(R.id.multipleDeleteItemBtn).setOnClickListener {
            startActivity(Intent(this, MultipleDeleteItemActivity::class.java))
        }
        findViewById<Button>(R.id.dialogBtn).setOnClickListener {
            startActivity(Intent(this, DialogActivity::class.java))
        }
        findViewById<Button>(R.id.expansionPanelBtn).setOnClickListener {
            startActivity(Intent(this, ExpansionPanelActivity::class.java))
        }
        findViewById<Button>(R.id.searchBarBtn).setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        findViewById<Button>(R.id.bottomNavigationViewBtn).setOnClickListener {
            startActivity(Intent(this, BottomNavigationViewActivity::class.java))
        }
        findViewById<Button>(R.id.sideBarNavigationViewBtn).setOnClickListener {
            startActivity(Intent(this, NavigationDrawerActivity::class.java))
        }
        findViewById<Button>(R.id.imageSliderBtn).setOnClickListener {
            startActivity(Intent(this, ImageSliderActivity::class.java))
        }
        findViewById<Button>(R.id.imagePickBtn).setOnClickListener {
            startActivity(Intent(this, ImagePickActivity::class.java))
        }
    }


}