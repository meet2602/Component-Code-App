package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.adapters.ExpandAdapter
import com.materialsouk.allcodeapp.models.ExpandModel
import java.util.*
import kotlin.collections.ArrayList

class SearchBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)
        val itemSearchInput: EditText = findViewById(R.id.item_search_input)
        val itemClearClickParent: RelativeLayout = findViewById(R.id.item_clear_click_parent)
        val itemMicClickParent: RelativeLayout = findViewById(R.id.item_mic_click_parent)
        val itemMenuClickParent: RelativeLayout = findViewById(R.id.item_menu_click_parent)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val arrayList :ArrayList<ExpandModel> = ArrayList()

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

        var adapter = ExpandAdapter(arrayList)

        recyclerView.adapter = adapter
        itemSearchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            @SuppressLint("NotifyDataSetChanged")
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (itemSearchInput.text.isNotEmpty()) {
                    if (itemClearClickParent.visibility != View.VISIBLE) {
                        itemClearClickParent.visibility = View.VISIBLE
                    }
                    val freeServiceModelArrayList: ArrayList<ExpandModel> = ArrayList()

                    for (i in arrayList) {
                        if (i.getName().lowercase(Locale.getDefault()).contains(
                                itemSearchInput.text.toString().lowercase(
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
                } else {
                    adapter = ExpandAdapter(arrayList)

                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                    itemClearClickParent.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })

        itemMicClickParent.setOnClickListener {
            Toast.makeText(this, "Mic clicked", Toast.LENGTH_SHORT).show()
        }
        itemMenuClickParent.setOnClickListener {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
        }
        itemClearClickParent.setOnClickListener {
            itemSearchInput.text.clear()
        }
    }
}