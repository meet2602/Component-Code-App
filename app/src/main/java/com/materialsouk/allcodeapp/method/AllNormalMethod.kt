package com.materialsouk.allcodeapp.method

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.lang.Exception


object AllNormalMethod {
    fun hideKeyboard(context: Context, view: View) {
        try {
            val imm: InputMethodManager =
                context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (ignored: Exception) {
        }
    }

    fun copyToClipBoard(context: Context, text: String) {
        val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
    }

    fun shareText(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        context.startActivity(Intent.createChooser(intent, "Share"))
    }

    fun shareImage(context: Context, pathToImage: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, pathToImage)
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun shareMultipleImage(context: Context, ImagePathList: ArrayList<Uri>) {
        val intent = Intent(Intent.ACTION_SEND_MULTIPLE)
        intent.type = "*/*"
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ImagePathList)
        context.startActivity(Intent.createChooser(intent, "Share via"))
    }

    fun pasteFromClipBoard(context: Context): String {
        val pasteText: String?
        val clipboard = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        pasteText = clipboard.primaryClip!!.getItemAt(0).text.toString()
        return pasteText
    }
}