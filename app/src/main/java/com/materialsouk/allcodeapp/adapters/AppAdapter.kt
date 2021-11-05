package com.materialsouk.allcodeapp.adapters

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.R
import com.materialsouk.allcodeapp.models.AppModel
import java.util.ArrayList

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast


class AppAdapter(private val context: Context, private var appModelList: ArrayList<AppModel>) :
    RecyclerView.Adapter<AppAdapter.ViewHolder>() {


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val appNameTxt: TextView = itemView.findViewById(R.id.list_app_name)
        val appPackageNameTxt: TextView = itemView.findViewById(R.id.app_package)
        val appIcon: ImageView = itemView.findViewById(R.id.app_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.installed_app_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.appNameTxt.text = appModelList[position].getName()
        holder.appIcon.setImageDrawable(appModelList[position].getIcon())
        holder.appPackageNameTxt.text = appModelList[position].getPackages()

        holder.itemView.setOnClickListener {
            val dialogListTitle = arrayOf("Open App", "App Info")
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("Choose Action")
                .setItems(
                    dialogListTitle
                ) { _, which ->
                    when (which) {
                        0 -> {
                            val intent =
                                context.packageManager.getLaunchIntentForPackage(appModelList[position].getPackages())
                            if (intent != null) {
                                context.startActivity(intent)
                            }else{
                                Toast.makeText(context,"System app is not open for any reason.",Toast.LENGTH_LONG).show()
                            }
                        }
                        1 -> {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            intent.data =
                                Uri.parse("package:${appModelList[position].getPackages()}")
                            context.startActivity(intent)
                        }
                    }
                }
            builder.show()
        }

    }

    override fun getItemCount(): Int {
        return appModelList.size
    }
}