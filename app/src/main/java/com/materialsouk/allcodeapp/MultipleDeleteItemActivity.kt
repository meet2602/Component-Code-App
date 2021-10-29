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
        arrayList.add("Meet")
        arrayList.add("Ankit")
        arrayList.add("Rushil")
        arrayList.add("Abhishek")
        arrayList.add("Modi")
        arrayList.add("Ghree")
        arrayList.add("Kirtan")
        arrayList.add("Ankita")
        arrayList.add("Soham")
        arrayList.add("Ganesh")
        arrayList.add("Dixit")
        arrayList.add("Aakash")
        arrayList.add("Parth")
        arrayList.add("Pranav")
        arrayList.add("Ankit Sir")
        arrayList.add("Priya Mem")
        arrayList.add("Jinal Mem")
        arrayList.add("Bhumi")
        arrayList.add("Nidhi")
        arrayList.add("Hardik")
        arrayList.add("Mayank")
        arrayList.add("Kaushik")
        arrayList.add("Rinku")
        arrayList.add("Mom")
        val adapter = MultipleSelectAdapter(this, arrayList, tvEmpty)

        recyclerView.adapter = adapter
    }
}