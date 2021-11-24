package com.materialsouk.allcodeapp

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DownloadManager
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.materialsouk.allcodeapp.method.Permission
import java.util.ArrayList


class DownloadAnyFileActivity : AppCompatActivity() {
    private val permissionsRequest: ArrayList<String> = arrayListOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    private val requestIdMultiplePermissions = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_any_file)
        val edFileName = findViewById<EditText>(R.id.edFileName)
        findViewById<Button>(R.id.downloadBtn).setOnClickListener {
            if (edFileName.text.toString().isNotEmpty()) {
                if (checkMultipleRequestPermissions()) {
                    doOperation()
                    downloadFile(this, Uri.parse(edFileName.text.toString()))
                }
            } else {
                edFileName.error = "Required"
            }
        }
    }

    private fun doOperation() {
        Toast.makeText(this, "Successfully granted", Toast.LENGTH_LONG).show()
    }

    private fun checkMultipleRequestPermissions(): Boolean {
        val listPermissionsNeeded: MutableList<String> = ArrayList()

        for (p in permissionsRequest) {
            val result = ContextCompat.checkSelfPermission(this, p)
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p)
            }
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                requestIdMultiplePermissions
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestIdMultiplePermissions) {
            if (grantResults.isNotEmpty()) {
                var isGrant = true
                for (element in grantResults) {
                    if (element == PackageManager.PERMISSION_DENIED) {
                        isGrant = false
                    }
                }
                if (isGrant) {
                    doOperation()
                } else {
                    var someDenied = false
                    for (permission in permissions) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                permission
                            )
                        ) {
                            if (ActivityCompat.checkSelfPermission(
                                    this,
                                    permission
                                ) == PackageManager.PERMISSION_DENIED
                            ) {
                                someDenied = true
                            }
                        }
                    }
                    if (someDenied) {
                        Permission.settingActivityOpen(this)
                    } else {
                        Permission.showDialogOK(this) { _: DialogInterface?, which: Int ->
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> checkMultipleRequestPermissions()
                                DialogInterface.BUTTON_NEGATIVE -> {
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun downloadFile(context: Context, url: Uri) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(url)
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationInExternalPublicDir(
            Environment.DIRECTORY_DOWNLOADS,
            url.lastPathSegment
        )
        downloadManager.enqueue(request)
    }
}