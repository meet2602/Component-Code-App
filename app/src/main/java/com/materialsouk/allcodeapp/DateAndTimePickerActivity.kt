package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimePickerActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_and_time_picker)
        val cal = Calendar.getInstance()
        findViewById<TextView>(R.id.dateTxt).text = "--/--/----"
        findViewById<TextView>(R.id.timeTxt).text = "--:--"
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                findViewById<TextView>(R.id.dateTxt).text = sdf.format(cal.time)
            }
        findViewById<Button>(R.id.datePickerBtn).setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val mTimePicker: TimePickerDialog
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minutes = mCurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this,
            { _, hourOfDay, minute ->
                val modifiedHour = getHourAMPM(hourOfDay)
                val pmAm = if (hourOfDay > 11) "PM" else "AM"
                findViewById<TextView>(R.id.timeTxt).text = "$modifiedHour:$minute $pmAm"
            }, hour, minutes, false)

        findViewById<Button>(R.id.timePickerBtn).setOnClickListener {
            mTimePicker.show()
        }

    }

    private fun getHourAMPM(hour: Int): Int {
        // Return the hour value for AM PM time format
        var modifiedHour = if (hour > 11) hour - 12 else hour
        if (modifiedHour == 0) {
            modifiedHour = 12
        }
        return modifiedHour
    }
}