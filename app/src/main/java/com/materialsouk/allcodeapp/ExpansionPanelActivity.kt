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
        arrayList.add(ExpandModel("Meet",false))
        arrayList.add(ExpandModel("Ankit",false))
        arrayList.add(ExpandModel("Rushil",false))
        arrayList.add(ExpandModel("Abhishek",false))
        arrayList.add(ExpandModel("Modi",false))
        arrayList.add(ExpandModel("Ghree",false))
        arrayList.add(ExpandModel("Kirtan",false))
        arrayList.add(ExpandModel("Ankita",false))
        arrayList.add(ExpandModel("Soham",false))
        arrayList.add(ExpandModel("Ganesh",false))
        arrayList.add(ExpandModel("Dixit",false))

        val adapter = ExpandAdapter( arrayList)

        recyclerView.adapter = adapter
    }
}