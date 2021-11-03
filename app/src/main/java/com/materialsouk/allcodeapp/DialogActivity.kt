package com.materialsouk.allcodeapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.view.View
import android.widget.*
import com.materialsouk.allcodeapp.method.AllNormalMethod.hideKeyboard



class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
        findViewById<Button>(R.id.progressDialogBtn).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setView(R.layout.progress)
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
        findViewById<Button>(R.id.loadingDialogBtn).setOnClickListener {
            val loadingDialog = Dialog(this)
            loadingDialog.setContentView(R.layout.loading)
            loadingDialog.window!!.setLayout(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            loadingDialog.show()
        }
        findViewById<Button>(R.id.alertDialogBtn).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Title")
            builder.setMessage("Message")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "clicked yes", Toast.LENGTH_LONG).show()
            }
            builder.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(this, "clicked cancel\n operation cancel", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "clicked No", Toast.LENGTH_LONG).show()
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        findViewById<Button>(R.id.materialAlertDialogBtn).setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Ok") { _, _ ->
                    Toast.makeText(this, "clicked ok", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    Toast.makeText(this, "clicked Cancel", Toast.LENGTH_LONG).show()
                }
                .show()
        }
        findViewById<Button>(R.id.themeDialogBtn).setOnClickListener {
            var checkedItem = 2
            val items = arrayOf("Light", "Dark", "Auto (System Default)")
            MaterialAlertDialogBuilder(this)
                .setTitle("Theme")
                .setPositiveButton("Ok") { _, _ ->
                    when (checkedItem) {
                        0 -> {
                            Toast.makeText(this, "Light Mode", Toast.LENGTH_LONG).show()
                        }
                        1 -> {
                            Toast.makeText(this, "Dark Mode", Toast.LENGTH_LONG).show()
                        }
                        else -> {
                            Toast.makeText(this, "Auto", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                .setSingleChoiceItems(items, checkedItem) { _, which ->
                    checkedItem = which
                }
                .setCancelable(false)
                .show()
        }


        val fileDialog = Dialog(this)
        fileDialog.setContentView(R.layout.custom_dialog)
        fileDialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val cancelBtn: Button = fileDialog.findViewById(R.id.cancel_btn)
        val saveBtn: Button = fileDialog.findViewById(R.id.save_btn)
        val edFileName: EditText = fileDialog.findViewById(R.id.edFileName)
        val fileTypeRG: RadioGroup = fileDialog.findViewById(R.id.fileTypeGroup)
        var fileTypeStr = "light"
        fileTypeRG.setOnCheckedChangeListener { _: RadioGroup?, checkedId: Int ->
            if (checkedId == R.id.radioIno) {
                fileTypeStr = "light"
            } else if (checkedId == R.id.radioTxt) {
                fileTypeStr = "dark"
            }
        }
        cancelBtn.setOnClickListener { v: View ->
            hideKeyboard(this,v)
            fileDialog.dismiss()
        }
        saveBtn.setOnClickListener { v: View ->
            if (edFileName.text.toString().trim().isEmpty()) {
                edFileName.error = "Required"
            } else {
                Toast.makeText(this, "${edFileName.text}\n${fileTypeStr}", Toast.LENGTH_LONG)
                    .show()
            }
            hideKeyboard(this,v)
            fileDialog.dismiss()
        }

        findViewById<Button>(R.id.customDialogBtn).setOnClickListener {
            fileDialog.show()
        }

    }

}