package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.materialsouk.allcodeapp.models.AppModel

import android.content.pm.PackageInfo

import android.content.pm.ApplicationInfo
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.materialsouk.allcodeapp.adapters.AppAdapter
import java.util.*
import kotlin.collections.ArrayList


class AllInstalledAppActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var installedAppsList: ArrayList<AppModel>
    private lateinit var installedAppAdapter: AppAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_installed_app)
        recyclerView = findViewById(R.id.recycler_view)
        val loadingDialog = Dialog(this)
        loadingDialog.setContentView(R.layout.loading)
        loadingDialog.window!!.setLayout(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        loadingDialog.setCancelable(false)
        installedAppsList = ArrayList()
        loadingDialog.show()
        Handler(Looper.getMainLooper()).postDelayed({
            getInstalledApps()
            loadingDialog.dismiss()
            findViewById<TextView>(R.id.totalInstalledApp).text =
                "${getString(R.string.total_Installed_Apps)} ${installedAppsList.size}"
            installedAppAdapter = AppAdapter(this, installedAppsList)
            recyclerView.adapter = installedAppAdapter
        }, 500)

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun getInstalledApps(): ArrayList<AppModel> {
        installedAppsList.clear()
        val packs = packageManager.getInstalledPackages(0)
        for (i in packs.indices) {
            val p = packs[i]
            if (!isSystemPackage(p)) {
                val appName = p.applicationInfo.loadLabel(packageManager).toString()
                val icon = p.applicationInfo.loadIcon(packageManager)
                val packages = p.applicationInfo.packageName
                installedAppsList.add(AppModel(appName, icon, packages))
            }
        }
        installedAppsList.sortBy { it.getName() }
        return installedAppsList
    }

    private fun isSystemPackage(pkgInfo: PackageInfo): Boolean {
        return pkgInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val search = menu.findItem(R.id.app_bar_search)

        val searchView = search.actionView as SearchView
        searchView.maxWidth = android.R.attr.width
        searchView.queryHint = "Search app name or package"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                val appModelArrayList: ArrayList<AppModel> = ArrayList()

                for (i in installedAppsList) {
                    if (i.getName().lowercase(Locale.getDefault()).contains(
                            newText!!.lowercase(
                                Locale.getDefault()
                            )
                        )
                        ||
                        i.getPackages().lowercase(Locale.getDefault()).contains(
                            newText.lowercase(
                                Locale.getDefault()
                            )
                        )
                    ) {
                        appModelArrayList.add(i)
                    }
                }
                installedAppAdapter =
                    AppAdapter(this@AllInstalledAppActivity, appModelArrayList)

                recyclerView.adapter = installedAppAdapter
                installedAppAdapter.notifyDataSetChanged()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}