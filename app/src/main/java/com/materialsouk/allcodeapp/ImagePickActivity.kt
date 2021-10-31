package com.materialsouk.allcodeapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.net.Uri

import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.materialsouk.allcodeapp.adapters.ImageUsPagerAdapter


class ImagePickActivity : AppCompatActivity() {
    private lateinit var urlArray: ArrayList<String>
    private lateinit var urlNameArray: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_pick)

        val mViewPager = findViewById<ViewPager>(R.id.viewPager)

        urlArray = ArrayList()
        urlNameArray = ArrayList()

        val singleImageResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // There are no request codes
                    val data: Intent? = result.data
                    val selectedImageUri: Uri? = data?.data
                    // Get the path from the Uri
                    val path = getPathFromURI(selectedImageUri)
                    findViewById<TextView>(R.id.textView).text = path
                    findViewById<ImageView>(R.id.imageView2).setImageURI(selectedImageUri)

                }
            }
        findViewById<Button>(R.id.oneImageSelectBtn).setOnClickListener {
            val intent = Intent()
            intent.apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
            }
            singleImageResultLauncher.launch(Intent.createChooser(intent, "Select Picture"))

        }
        val multipleImageResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // There are no request codes
                    val data: Intent? = result.data
                    if (data?.clipData != null) {
                        val count = data.clipData!!.itemCount
                        for (i in 0 until count) {
                            val imageUri: Uri = data.clipData!!.getItemAt(i).uri
                            val path = getPathFromURI(imageUri)
                            Log.d("$i", path)
                            urlArray.add(imageUri.toString())
                            urlNameArray.add(path)
                        }
                    } else if (data?.data != null) {
                        val imagePath: Uri? = data.data
                        val path = getPathFromURI(imagePath)
                        Log.d("imagePath", path)
                        urlArray.add(imagePath.toString())
                        urlNameArray.add(path)
                    }
                    findViewById<TextView>(R.id.textView1).text = urlNameArray.toString()
                    val mViewPagerAdapter = ImageUsPagerAdapter(false, this, null, urlArray)
                    mViewPager.adapter = mViewPagerAdapter
                }
            }
        findViewById<Button>(R.id.multipleImageSelectBtn).setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.apply {
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/*"
            }
            multipleImageResultLauncher.launch(
                Intent.createChooser(
                    intent,
                    "Select Multiple Picture"
                )
            )
        }

    }

    private fun getPathFromURI(uri: Uri?): String {
        var path = ""
        if (contentResolver != null) {
            val cursor = contentResolver.query(uri!!, null, null, null, null)
            if (cursor != null) {
                cursor.moveToFirst()
                val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME)
                path = cursor.getString(idx)
                cursor.close()
            }
        }
        return path
    }

}