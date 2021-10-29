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
        arrayList.add(ExpandModel("Ankash", false))
        arrayList.add(ExpandModel("Parth", false))
        arrayList.add(ExpandModel("Pranav", false))
        arrayList.add(ExpandModel("Ankit Sir", false))
        arrayList.add(ExpandModel("Priya Mem", false))
        arrayList.add(ExpandModel("Jinal Mem", false))
        arrayList.add(ExpandModel("Bhumi", false))
        arrayList.add(ExpandModel("Nidhi", false))
        arrayList.add(ExpandModel("Hardik", false))
        arrayList.add(ExpandModel("Mayank", false))
        arrayList.add(ExpandModel("Kaushik", false))
        arrayList.add(ExpandModel("Rinku", false))
        arrayList.add(ExpandModel("Mom", false))

        val adapter = ExpandAdapter( arrayList)

        recyclerView.adapter = adapter
    }
}