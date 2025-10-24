package com.example.qrgrant.ui.screen.batch

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.qrgrant.R
import com.example.qrgrant.data.model.BatchModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BatchViewModel(application: Application) : AndroidViewModel(application) {
    private val _batches = MutableStateFlow<List<BatchModel>>(emptyList())
    val batches: StateFlow<List<BatchModel>> = _batches

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun loadData(context: Context) {
        _isLoading.value = true
        val json =
            context.resources.openRawResource(R.raw.batchs).bufferedReader().use { it.readText() }

        val gson = Gson()

        val type = object : TypeToken<List<BatchModel>>() {}.type
        val list: List<BatchModel> = gson.fromJson(json, type)

        _batches.value = list
        delay(500)
        _isLoading.value = false
    }
}
