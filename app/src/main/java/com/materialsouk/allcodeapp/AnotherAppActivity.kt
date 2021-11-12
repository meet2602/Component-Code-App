package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.materialsouk.allcodeapp.method.AllNormalMethod.shareText
import android.content.pm.PackageManager
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task


class AnotherAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another_app)
        findViewById<Button>(R.id.shareAppBtn).setOnClickListener {
            val appName = "https://play.google.com/store/apps/details?id=$packageName"
            shareText(this, appName)
        }
        findViewById<Button>(R.id.playStoreBtn).setOnClickListener {
            redirectToPlayStore()
        }
        findViewById<Button>(R.id.instagramBtn).setOnClickListener {
            val profilePath = "https://instagram.com/meetb2602"
            val appPackageName = "com.instagram.android"
            redirectToAnotherApp(this, appPackageName, profilePath)
        }

        findViewById<Button>(R.id.facebookBtn).setOnClickListener {
            val profilePath = "https://www.facebook.com/meet.bhavsar.52831"
            val appPackageName = "com.facebook.katana"
            redirectToAnotherApp(this, appPackageName, profilePath)
        }
        findViewById<Button>(R.id.gmailBtn).setOnClickListener {
            val toEmail = "meetxxxxxxx@gmail.com"
            val email = Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("mailto:$toEmail"))
                .setPackage("com.google.android.gm")
            startActivity(email)

        }
        findViewById<Button>(R.id.githubBtn).setOnClickListener {
            val profilePath = "https://github.com/meet2602"
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(profilePath)
                )
            )
        }
        val reviewManager = ReviewManagerFactory.create(this)
        findViewById<Button>(R.id.rateUsBtn).setOnClickListener {
            showRateApp(reviewManager)
        }
    }

    private fun showRateApp(reviewManager: ReviewManager) {
        val request = reviewManager.requestReviewFlow()
        request.addOnCompleteListener { task: Task<ReviewInfo?> ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                val flow =
                    reviewManager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener {
                    Toast.makeText(
                        applicationContext,
                        "In App Rating completed!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                showRateAppFallbackDialog()
            }
        }
    }

    private fun showRateAppFallbackDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.rate_app_title)
            .setMessage(R.string.rate_app_message)
            .setPositiveButton(R.string.rate_btn_pos) { _, _ -> redirectToPlayStore() }
            .setNegativeButton(
                R.string.rate_btn_neg
            ) { _, _ -> }
            .setNeutralButton(
                R.string.rate_btn_nut
            ) { _, _ -> }
            .setOnDismissListener { }
            .show()
    }

    private fun redirectToPlayStore() {
        val appName = "https://play.google.com/store/apps/details?id=$packageName"
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$packageName")
                )
            )
        } catch (exception: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(appName)
                )
            )
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun isIntentAvailable(ctx: Context, intent: Intent): Boolean {
        val packageManager: PackageManager = ctx.packageManager
        val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        return list.size > 0
    }

    private fun redirectToAnotherApp(context: Context, packageName: String, profilePath: String) {
        val uri = Uri.parse(profilePath)
        val insta = Intent(Intent.ACTION_VIEW, uri)
        insta.setPackage(packageName)

        if (isIntentAvailable(context, insta)) {
            startActivity(insta)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}