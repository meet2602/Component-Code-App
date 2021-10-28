package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.adapters.MultipleSelectAdapter
import java.util.*

class MultipleDeleteItemActivity : AppCompatActivity() {
    var arrayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiple_delete_item)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val tvEmpty = findViewById<TextView>(R.id.tv_empty)
        arrayList.addAll(listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11","12","13","14","15","16","17","18","19","20","21"))
        val adapter = MultipleSelectAdapter(this, arrayList, tvEmpty)

        recyclerView.adapter = adapter
    }
}