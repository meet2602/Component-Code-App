package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.adapters.ExpandAdapter
import com.materialsouk.allcodeapp.models.ExpandModel


class SearchWithMenuActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_with_menu)

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView
        searchView.maxWidth = android.R.attr.width
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

}