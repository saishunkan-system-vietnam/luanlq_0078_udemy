package com.example.qrgrant.ui.screen.license

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.qrgrant.R
import com.example.qrgrant.data.model.LicenseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LicenseViewModel : ViewModel() {
    private val _licenses = MutableStateFlow<List<LicenseModel>>(emptyList())
    val license: StateFlow<List<LicenseModel>> = _licenses

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun loadData(context: Context) {
        _isLoading.value = true
        val json = context.resources.openRawResource(R.raw.aboutlibraries).bufferedReader()
            .use { it.readText() }

        val gson = Gson()
        val jsonObject = gson.fromJson(json, Map::class.java)
        val librariesJson = gson.toJsonTree(jsonObject["libraries"])

        val type = object : TypeToken<List<LicenseModel>>() {}.type
        val list: List<LicenseModel> = gson.fromJson(librariesJson, type)

        _licenses.value = list
        delay(500)
        _isLoading.value = false
    }

}
