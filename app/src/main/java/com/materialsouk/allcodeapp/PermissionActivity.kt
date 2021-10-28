package com.materialsouk.allcodeapp

import android.Manifest.permission.*
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.widget.Toast
import com.materialsouk.allcodeapp.method.Permission.checkSinglePermission
import com.materialsouk.allcodeapp.method.Permission.settingActivityOpen
import com.materialsouk.allcodeapp.method.Permission.showDialogOK
import java.util.ArrayList


class PermissionActivity : AppCompatActivity() {
    private val requestIdMultiplePermissions = 1
    private val singlePermissions = 2
    private val singlePermissionRequest = READ_CONTACTS
    private val permissionsRequest: ArrayList<String> = arrayListOf(READ_CALENDAR, CAMERA)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        findViewById<Button>(R.id.onePermissionBtn).setOnClickListener {
            if (checkSinglePermission(
                    this,
                    singlePermissionRequest,
                    singlePermissions
                )
            ) {
                doOperation()
            }
        }
        findViewById<Button>(R.id.multiplePermissionBtn).setOnClickListener {
            if (checkMultipleRequestPermissions()) {
                doOperation()
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
                        settingActivityOpen(this)
                    } else {
                        showDialogOK(this) { _: DialogInterface?, which: Int ->
                            when (which) {
                                DialogInterface.BUTTON_POSITIVE -> checkMultipleRequestPermissions()
                                DialogInterface.BUTTON_NEGATIVE -> {
                                }
                            }
                        }
                    }
                }
            }
        } else if (requestCode == singlePermissions) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                doOperation()
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        singlePermissionRequest
                    )
                ) {
                    if (ActivityCompat.checkSelfPermission(
                            this,
                            singlePermissionRequest
                        ) == PackageManager.PERMISSION_DENIED
                    ) {
                        settingActivityOpen(this)
                    }
                } else {
                    showDialogOK(this) { _: DialogInterface?, which: Int ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                if (checkSinglePermission(
                                        this,
                                        singlePermissionRequest,
                                        singlePermissions
                                    )
                                ) {
                                    doOperation()
                                }
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                            }
                        }
                    }

                }
            }
        }
    }

}
