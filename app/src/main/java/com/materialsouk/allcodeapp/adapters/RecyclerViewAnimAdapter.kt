package com.materialsouk.allcodeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.R
import java.util.ArrayList

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.materialsouk.allcodeapp.models.ExpandModel


class RecyclerViewAnimAdapter(private var expandList: ArrayList<ExpandModel>, animRes: Int) :
    RecyclerView.Adapter<RecyclerViewAnimAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val srNo: TextView = itemView.findViewById(R.id.srNo)
        val nameTxt: TextView = itemView.findViewById(R.id.part_name)
        val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandableLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.expand_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.srNo.text = (position + 1).toString()
        holder.nameTxt.text = expandList[position].getName()

    }

    override fun getItemCount(): Int {
        return expandList.size
    }
}