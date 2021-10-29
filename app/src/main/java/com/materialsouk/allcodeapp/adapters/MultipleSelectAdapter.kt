package com.materialsouk.allcodeapp.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.R
import com.materialsouk.allcodeapp.models.MainViewModel
import java.util.ArrayList

class MultipleSelectAdapter(
    private val activity: Activity,
    private val arrayList: ArrayList<String>,
    private val tvEmpty: TextView?
) : RecyclerView.Adapter<MultipleSelectAdapter.ViewHolder>() {

    var mainViewModel: MainViewModel? = null
    var isEnable = false
    var isSelectAll = false
    var selectList = ArrayList<String>()

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val srNo: TextView = itemView.findViewById(R.id.srNo)
        val textView: TextView = itemView.findViewById(R.id.text_view)
        val ivCheck: ImageView = itemView.findViewById(R.id.iv_check_box)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        mainViewModel =
            ViewModelProvider((activity as FragmentActivity?)!!)[MainViewModel::class.java]

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.srNo.text = (position+1).toString()
        holder.textView.text = arrayList[position]
        holder.itemView.setOnLongClickListener { v: View ->
            if (!isEnable) {
                val callback: ActionMode.Callback =
                    object : ActionMode.Callback {
                        override fun onCreateActionMode(
                            mode: ActionMode,
                            menu: Menu
                        ): Boolean {
                            val menuInflater = mode.menuInflater
                            menuInflater.inflate(R.menu.select_menu, menu)
                            return true
                        }

                        override fun onPrepareActionMode(
                            mode: ActionMode,
                            menu: Menu
                        ): Boolean {
                            isEnable = true
                            clickItem(holder)
                            mainViewModel!!.text.observe((activity as LifecycleOwner?)!!) { s ->
                                mode.title = java.lang.String.format(
                                    "%s Selected",
                                    s
                                )
                            }
                            return true
                        }

                        @SuppressLint("NonConstantResourceId", "NotifyDataSetChanged")
                        override fun onActionItemClicked(
                            mode: ActionMode,
                            item: MenuItem
                        ): Boolean {
                            when (item.itemId) {
                                R.id.menu_delete -> {
                                    for (s in selectList) {
                                        arrayList.remove(s)
                                    }
                                    if (arrayList.size == 0) {
                                        tvEmpty!!.visibility = View.VISIBLE
                                    }
                                    mode.finish()
                                }
                                R.id.menu_select_all -> {
                                    if (selectList.size == arrayList.size) {
                                        isSelectAll = false
                                        selectList.clear()
                                    } else {
                                        isSelectAll = true
                                        selectList.clear()
                                        selectList.addAll(arrayList)
                                    }
                                    mainViewModel!!.setText(selectList.size.toString())
                                    notifyDataSetChanged()
                                }
                            }
                            return true
                        }

                        @SuppressLint("NotifyDataSetChanged")
                        override fun onDestroyActionMode(mode: ActionMode) {
                            isEnable = false
                            isSelectAll = false
                            selectList.clear()
                            notifyDataSetChanged()
                        }
                    }
                (v.context as AppCompatActivity).startActionMode(callback)
            } else {
                clickItem(holder)
            }
            true
        }
        holder.itemView.setOnClickListener {
            if (isEnable) {
                clickItem(holder)
            }
        }
        if (isSelectAll) {
            holder.ivCheck.visibility = View.VISIBLE
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.ivCheck.visibility = View.GONE
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun clickItem(holder: ViewHolder) {
        val s = arrayList[holder.adapterPosition]
        if (holder.ivCheck.visibility == View.GONE) {
            holder.ivCheck.visibility = View.VISIBLE
            holder.itemView.setBackgroundColor(Color.LTGRAY)
            selectList.add(s)
        } else {
            holder.ivCheck.visibility = View.GONE
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
            selectList.remove(s)
        }
        mainViewModel!!.setText(selectList.size.toString())
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}