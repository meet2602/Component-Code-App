package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.adapters.ExpandAdapter
import com.materialsouk.allcodeapp.models.ExpandModel

class ExpansionPanelActivity : AppCompatActivity() {
    private lateinit var arrayList: ArrayList<ExpandModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expansion_panel)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        arrayList = ArrayList()

        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        arrayList.add(ExpandModel(false))
        val adapter = ExpandAdapter( arrayList)

        recyclerView.adapter = adapter
    }
}