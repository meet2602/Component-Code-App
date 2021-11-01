package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.adapters.RecyclerViewAnimAdapter
import com.materialsouk.allcodeapp.models.ExpandModel

class RecyclerViewAnimationActivity : AppCompatActivity() {

    private val animationRes = arrayOf(
        R.anim.layout_animation_up_to_down,
        R.anim.layout_animation_down_to_up,
        R.anim.layout_animation_left_to_right,
        R.anim.layout_animation_right_to_left,
        R.anim.up_to_down,
        R.anim.down_to_up,
        R.anim.left_to_right,
        R.anim.right_to_left
    )

    var positions = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_animation)

        val animationName = arrayOf(
            resources.getString(R.string.up_to_down),
            resources.getString(R.string.down_to_up),
            resources.getString(R.string.left_to_right),
            resources.getString(R.string.right_to_left),
            resources.getString(R.string.item_up_to_down),
            resources.getString(R.string.item_down_to_up),
            resources.getString(R.string.item_left_to_right),
            resources.getString(R.string.item_right_to_left),
        )
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val arrayList = ArrayList<ExpandModel>()
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

        val spin = findViewById<Spinner>(R.id.spinner)
        findViewById<Button>(R.id.refreshBtn).setOnClickListener {
            val adapter = RecyclerViewAnimAdapter(arrayList, animationRes[positions], positions)
            if (positions < 4) {
                recyclerView.apply {
                    layoutAnimation =
                        AnimationUtils.loadLayoutAnimation(context, animationRes[positions])
                }
            }
            recyclerView.adapter = adapter
        }
        spin.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                positions = position
                val adapter = RecyclerViewAnimAdapter(arrayList, animationRes[position], positions)
                if (positions < 4) {
                    recyclerView.apply {
                        layoutAnimation =
                            AnimationUtils.loadLayoutAnimation(context, animationRes[position])
                    }
                }
                recyclerView.adapter = adapter
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        })
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            animationName
        )
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spin.adapter = ad
    }
}