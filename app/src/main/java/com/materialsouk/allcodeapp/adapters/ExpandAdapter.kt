package com.materialsouk.allcodeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.R
import java.util.ArrayList
import android.view.animation.Animation.RELATIVE_TO_SELF

import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.materialsouk.allcodeapp.models.ExpandModel


class ExpandAdapter(private val expandList: ArrayList<ExpandModel>) :
    RecyclerView.Adapter<ExpandAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameTxt: TextView = itemView.findViewById(R.id.part_name)
        val arrow: ImageView = itemView.findViewById(R.id.arrow)
        val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandableLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.expand_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTxt.text = expandList[position].getName()
        val isExpanded: Boolean = expandList[position].getExpanded()

        if (isExpanded) {
            holder.expandableLayout.visibility = View.VISIBLE
            val rotate = RotateAnimation(360F, 180F, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            holder.arrow.animation = rotate
        } else {
            holder.expandableLayout.visibility = View.GONE
            val rotate = RotateAnimation(180F, 360F, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
            rotate.duration = 300
            rotate.fillAfter = true
            holder.arrow.animation = rotate
        }
        holder.itemView.setOnClickListener {
            expandList[position].setExpanded(!expandList[position].getExpanded())
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return expandList.size
    }
}