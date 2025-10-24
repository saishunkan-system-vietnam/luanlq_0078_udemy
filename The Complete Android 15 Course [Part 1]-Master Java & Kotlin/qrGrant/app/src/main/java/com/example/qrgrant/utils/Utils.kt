package com.example.qrgrant.utils

import java.text.NumberFormat
import java.util.Locale

object Utils {
    fun formatNumber(s: String): String {
        val number = if (s.isEmpty()) 0 else s.toInt()
        return NumberFormat.getNumberInstance(Locale.JAPAN).format(number)
    }
}