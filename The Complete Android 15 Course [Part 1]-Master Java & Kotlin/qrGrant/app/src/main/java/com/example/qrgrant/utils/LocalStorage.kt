package com.example.qrgrant.utils

import android.content.Context
import android.content.SharedPreferences

object LocalStorage {
    private const val PREF_NAME = "qr_prefs"
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setData(key: String, value: String, type: String) {
        when (type) {
            "String" -> prefs.edit().putString(key, value).apply()
            "Int" -> prefs.edit().putInt(key, value.toInt()).apply()
            "Boolean" -> prefs.edit().putBoolean(key, value.toBoolean()).apply()
            "Float" -> prefs.edit().putFloat(key, value.toFloat()).apply()
            "Long" -> prefs.edit().putLong(key, value.toLong()).apply()
            "ListString" -> {
                val list = value.split(",").map { it.trim() }
                prefs.edit().putStringSet(key, list.toSet()).apply()
            }
        }
    }

    fun getData(key: String, type: String): Any? {
        return when (type) {
            "String" -> prefs.getString(key, null)
            "Int" -> prefs.getInt(key, -1)
            "Boolean" -> prefs.getBoolean(key, false)
            "Float" -> prefs.getFloat(key, -1f)
            "Long" -> prefs.getLong(key, -1L)
            "ListString" -> prefs.getStringSet(key, emptySet<String>())?.toList()
                ?: emptyList<String>()

            else -> null
        }
    }

    fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }
}
