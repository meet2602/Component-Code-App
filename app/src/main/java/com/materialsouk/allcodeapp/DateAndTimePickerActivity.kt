package com.materialsouk.allcodeapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimePickerActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_and_time_picker)

        // todo: Simple date picker code
        val cal = Calendar.getInstance()
        findViewById<TextView>(R.id.dateTxt).text = "--/--/----"
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd-MMM-yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                findViewById<TextView>(R.id.dateTxt).text = sdf.format(cal.time)
            }
        findViewById<Button>(R.id.simpleDatePickerBtn).setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        // todo: Simple date picker code

        // todo: Material date picker code
        val materialDatePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
              //  .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()
        materialDatePicker.addOnPositiveButtonClickListener {
            val myFormat = "dd-MMM-yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            findViewById<TextView>(R.id.dateTxt).text = sdf.format(materialDatePicker.selection)
        }
        findViewById<Button>(R.id.materialDatePickerBtn).setOnClickListener {
            materialDatePicker.show(supportFragmentManager,"MATERIAL_DATE_PICKER")
        }
        // todo: Material date picker code



        // todo: Simple time picker code
        findViewById<TextView>(R.id.timeTxt).text = "--:--"
        val mTimePicker: TimePickerDialog
        val mCurrentTime = Calendar.getInstance()
        val hour = mCurrentTime.get(Calendar.HOUR_OF_DAY)
        val minutes = mCurrentTime.get(Calendar.MINUTE)

        mTimePicker = TimePickerDialog(this,
            { _, hourOfDay, minute ->
                val modifiedHour = getHourAMPM(hourOfDay)
                val pmAm = if (hourOfDay > 11) "PM" else "AM"
                val f: NumberFormat = DecimalFormat("00")
                findViewById<TextView>(R.id.timeTxt).text = "${f.format(modifiedHour)}:${f.format(minute)} $pmAm"

            }, hour, minutes, false)

        findViewById<Button>(R.id.simpleTimePickerBtn).setOnClickListener {
            mTimePicker.show()
        }
        // todo: Simple time picker code

        // todo: Material time picker code
        val materialTimePicker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setTitleText("Select Time")
                .build()
        materialTimePicker.addOnPositiveButtonClickListener{
            val pickedHour: Int = materialTimePicker.hour
            val pickedMinute: Int = materialTimePicker.minute
            val modifiedHour = getHourAMPM(pickedHour)
            val f: NumberFormat = DecimalFormat("00")
            val pmAm = if (pickedHour > 11) "PM" else "AM"
            findViewById<TextView>(R.id.timeTxt).text = "${f.format(modifiedHour)}:${f.format(pickedMinute)} $pmAm"
        }
        findViewById<Button>(R.id.materialTimePickerBtn).setOnClickListener {
            materialTimePicker.show(supportFragmentManager, "MATERIAL_TIME_PICKER")
        }
        // todo: Material time picker code

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