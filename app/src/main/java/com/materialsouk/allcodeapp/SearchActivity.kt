package com.materialsouk.allcodeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        findViewById<Button>(R.id.searchBarBtn).setOnClickListener {
            startActivity(Intent(this, SearchBarActivity::class.java))
        }
        findViewById<Button>(R.id.searchViewWithMenuBtn).setOnClickListener {
            startActivity(Intent(this, SearchWithMenuActivity::class.java))
        }
    }
}