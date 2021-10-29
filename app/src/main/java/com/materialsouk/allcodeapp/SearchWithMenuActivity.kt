package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.adapters.ExpandAdapter
import com.materialsouk.allcodeapp.models.ExpandModel
import java.util.*
import kotlin.collections.ArrayList


class SearchWithMenuActivity : AppCompatActivity() {
    private lateinit var arrayList: ArrayList<ExpandModel>
    private lateinit var adapter: ExpandAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_with_menu)

        recyclerView = findViewById(R.id.recycler_view)
        arrayList = ArrayList()
        arrayList.add(ExpandModel("Meet", false))
        arrayList.add(ExpandModel("Ankit", false))
        arrayList.add(ExpandModel("Rushil", false))
        arrayList.add(ExpandModel("Abhishek", false))
        arrayList.add(ExpandModel("Modi", false))
        arrayList.add(ExpandModel("Ghree", false))
        arrayList.add(ExpandModel("Kirtan", false))
        arrayList.add(ExpandModel("Ankita", false))
        arrayList.add(ExpandModel("Soham", false))
        arrayList.add(ExpandModel("Ganesh", false))
        arrayList.add(ExpandModel("Dixit", false))

        adapter = ExpandAdapter(arrayList)

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

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                val freeServiceModelArrayList: ArrayList<ExpandModel> = ArrayList()

                for (i in arrayList) {
                    if (i.getName().lowercase(Locale.getDefault()).contains(
                            newText!!.lowercase(
                                Locale.getDefault()
                            )
                        )
                    ) {
                        freeServiceModelArrayList.add(i)
                    }
                }
                adapter = ExpandAdapter(freeServiceModelArrayList)

                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}