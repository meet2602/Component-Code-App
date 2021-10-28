package com.materialsouk.allcodeapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.materialsouk.allcodeapp.fragments.AlbumFragment
import com.materialsouk.allcodeapp.fragments.HomeFragment
import com.materialsouk.allcodeapp.fragments.VideoFragment

class ViewPagerAdapter(fragment: FragmentActivity, private val size: Int) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = size

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumFragment()
            1 -> HomeFragment()
            2 -> VideoFragment()
            else -> HomeFragment()
        }
    }
}