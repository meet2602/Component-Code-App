package com.materialsouk.allcodeapp.adapters


import android.content.Context
import android.view.*
import androidx.viewpager.widget.PagerAdapter
import com.materialsouk.allcodeapp.R
import com.materialsouk.allcodeapp.ZoomClass
import java.util.*

internal class ImageUsPagerAdapter(context: Context, private val images: IntArray) :
    PagerAdapter() {

    private val mLayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?


    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater!!.inflate(R.layout.image_item, container, false)
        val imageView: ZoomClass = itemView.findViewById(R.id.imageViewMain)

        imageView.setImageResource(images[position])
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


}