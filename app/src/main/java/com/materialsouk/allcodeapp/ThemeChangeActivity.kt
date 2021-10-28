package com.materialsouk.allcodeapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ThemeChangeActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val items = arrayOf("Light", "Dark", "Auto (System Default)")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme_change)
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE)
        themeChangeText(findViewById(R.id.themeStateTxt))
        findViewById<LinearLayout>(R.id.themeChangeLayout).setOnClickListener { themeDialog() }
    }

    private fun themeChangeText(themeStateTxt: TextView) {
        when (sharedPreferences.getInt("night_mode", 2)) {
            0 -> themeStateTxt.text = items[0]
            1 -> themeStateTxt.text = items[1]
            else -> themeStateTxt.text = items[2]
        }
    }

    private fun themeDialog() {
        var checkedItem = sharedPreferences.getInt("night_mode", 2)

        MaterialAlertDialogBuilder(this)
            .setTitle("Theme")
            .setPositiveButton("Ok") { _, _ ->
                when (checkedItem) {
                    0 -> {
                        sharedPreferences.edit().putInt("night_mode", 0).apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    1 -> {
                        sharedPreferences.edit().putInt("night_mode", 1).apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else -> {
                        sharedPreferences.edit().putInt("night_mode", 2).apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    }
                }
                themeChangeText(findViewById(R.id.themeStateTxt))
            }
            .setSingleChoiceItems(items, checkedItem) { _, which ->
                checkedItem = which
            }
            .setCancelable(false)
            .show()
    }
}