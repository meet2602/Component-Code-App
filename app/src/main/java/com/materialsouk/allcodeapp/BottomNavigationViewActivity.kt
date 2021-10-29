package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.materialsouk.allcodeapp.fragments.AlbumFragment
import com.materialsouk.allcodeapp.fragments.HomeFragment
import com.materialsouk.allcodeapp.fragments.VideoFragment

class BottomNavigationViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation_view)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        setCurrentFragment(HomeFragment())
        bottomNavigationView.selectedItemId = R.id.home
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.album -> setCurrentFragment(AlbumFragment())
                R.id.home -> setCurrentFragment(HomeFragment())
                R.id.video -> setCurrentFragment(VideoFragment())
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}