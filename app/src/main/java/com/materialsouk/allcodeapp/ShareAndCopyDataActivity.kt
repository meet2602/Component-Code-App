package com.materialsouk.allcodeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.materialsouk.allcodeapp.method.AllNormalMethod.copyToClipBoard
import com.materialsouk.allcodeapp.method.AllNormalMethod.pasteFromClipBoard

import com.materialsouk.allcodeapp.method.AllNormalMethod.shareText


class ShareAndCopyDataActivity : AppCompatActivity() {
    private lateinit var edFileName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_and_copy_data)

        edFileName = findViewById(R.id.edFileName)
        findViewById<Button>(R.id.shareDataBtn).setOnClickListener {
            if (edFileName.text.isNotEmpty()) {
               shareText(this,edFileName.text.toString())
            } else {
                edFileName.error = "Required for share and copy"
            }
        }
        findViewById<Button>(R.id.copyDataBtn).setOnClickListener {
            if (edFileName.text.isNotEmpty()) {
                copyToClipBoard(
                    this,
                    edFileName.text.toString()
                )
                Toast.makeText(this, "copied", Toast.LENGTH_LONG).show()
            } else {
                edFileName.error = "Required for share and copy"
            }
        }
        findViewById<Button>(R.id.pasteDataBtn).setOnClickListener {
            edFileName.setText(pasteFromClipBoard(this))
            Toast.makeText(this, "Pasted", Toast.LENGTH_LONG).show()
        }
    }
}