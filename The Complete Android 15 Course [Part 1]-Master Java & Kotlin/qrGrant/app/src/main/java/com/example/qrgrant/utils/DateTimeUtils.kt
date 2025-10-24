package com.example.qrgrant.utils

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    fun showDateTimePicker(
        context: Context,
        pickTime: Boolean = false,
        currentValue: String? = null,
        onSelected: (String) -> Unit
    ) {
        val calendar = Calendar.getInstance()

        if (!currentValue.isNullOrBlank()) {
            try {
                val pattern = if (currentValue.length > 10) "yyyy-MM-dd HH:mm" else "yyyy-MM-dd"
                val sdf = SimpleDateFormat(pattern, Locale.US)
                val parsedDate = sdf.parse(currentValue)
                parsedDate?.let {
                    calendar.time = it
                }
            } catch (_: ParseException) {
            }
        }

        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                if (pickTime) {
                    TimePickerDialog(
                        context,
                        { _, hour, minute ->
                            calendar.set(Calendar.HOUR_OF_DAY, hour)
                            calendar.set(Calendar.MINUTE, minute)
                            val formatted = formatFromCalendar(calendar, true)
                            onSelected(formatted)
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true
                    ).show()
                } else {
                    val formatted = formatFromCalendar(calendar)
                    onSelected(formatted)
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun formatFromCalendar(calendar: Calendar, withTime: Boolean = false): String {
        val pattern = if (withTime) "yyyy-MM-dd HH:mm" else "yyyy-MM-dd"
        val sdf = SimpleDateFormat(pattern, Locale.US)
        return sdf.format(calendar.time)
    }
}
