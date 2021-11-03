package com.materialsouk.allcodeapp.adapters


import android.content.Context
import android.net.Uri
import android.view.*
import androidx.viewpager.widget.PagerAdapter
import com.materialsouk.allcodeapp.R
import com.materialsouk.allcodeapp.method.ZoomClass
import java.util.*
import kotlin.collections.ArrayList

class ImageUsPagerAdapter(private val isUrl:Boolean, context: Context, private val intImages: IntArray?, private val urlImage: ArrayList<String>?) :
    PagerAdapter() {

    private val mLayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?


    override fun getCount(): Int {
        return if (isUrl){
            intImages!!.size
        } else{
            urlImage!!.size
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View = mLayoutInflater!!.inflate(R.layout.image_item, container, false)
        val imageView: ZoomClass = itemView.findViewById(R.id.imageViewMain)

        if (isUrl) {
            imageView.setImageResource(intImages!![position])
        }else{
            imageView.setImageURI(Uri.parse(urlImage!![position]))
        }
        Objects.requireNonNull(container).addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


}