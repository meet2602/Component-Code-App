package com.materialsouk.allcodeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.R
import java.util.ArrayList

import android.widget.TextView
import com.materialsouk.allcodeapp.models.ExpandModel


class RecyclerViewAnimAdapter(
    private var expandList: ArrayList<ExpandModel>,
    private var animRes: Int,
    private var itemAnimPosition: Int
) :
    RecyclerView.Adapter<RecyclerViewAnimAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val srNo: TextView = itemView.findViewById(R.id.srNo)
        val nameTxt: TextView = itemView.findViewById(R.id.part_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.expand_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemAnimPosition > 3) {
            holder.itemView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, animRes)
        }
        holder.srNo.text = (position + 1).toString()
        holder.nameTxt.text = expandList[position].getName()

    }

    override fun getItemCount(): Int {
        return expandList.size
    }
}