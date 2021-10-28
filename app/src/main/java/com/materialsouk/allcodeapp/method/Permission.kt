package com.materialsouk.allcodeapp.method

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object Permission {
    fun checkSinglePermission(
        activity: Activity,
        permission: String,
        permissionCode: Int
    ): Boolean {
        if (ContextCompat.checkSelfPermission(
                activity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(permission),
                permissionCode
            )
        } else {
            return true
        }
        return false
    }

    fun settingActivityOpen(activity: Activity) {
        Toast.makeText(
            activity,
            "Go to settings and enable permissions",
            Toast.LENGTH_LONG
        )
            .show()
        val i = Intent()
        i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        i.addCategory(Intent.CATEGORY_DEFAULT)
        val packageName= activity.packageName
        i.data = Uri.parse("package:$packageName")
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        activity.startActivity(i)
    }

    fun showDialogOK(activity: Activity,okListener: DialogInterface.OnClickListener) {
        MaterialAlertDialogBuilder(activity)
            .setMessage("All Permission required for this app")
            .setPositiveButton("OK", okListener)
            .setNegativeButton("Cancel", okListener)
            .create()
            .show()
    }
}