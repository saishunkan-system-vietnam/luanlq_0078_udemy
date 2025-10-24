package com.example.qrgrant.ui.screen.apply.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.qrgrant.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class ApplyViewModel : ViewModel() {
    private val _scannerEnabled = MutableStateFlow(true)
    val scannerEnabled: StateFlow<Boolean> = _scannerEnabled
    private val _listCoupon = MutableStateFlow<List<String>>(emptyList())
    val listCoupon: StateFlow<List<String>> = _listCoupon

    private fun stopScanner() {
        _scannerEnabled.value = false
    }

    private suspend fun clearList() {
        delay(300)
        _listCoupon.value = emptyList()
    }

    suspend fun handleDataQr(context: Context, value: String, onSuccess: (data: String) -> Unit) {
        if (_listCoupon.value.contains(value)) return

        if (_listCoupon.value.size > 1000) return
        playBeep(context)
        _listCoupon.value += value

        if (_listCoupon.value.size == 1000) {
            stopScanner()
            onSuccess(handleData())
            clearList()
        }
    }

    private suspend fun handleData(): String {
        val json = Json.encodeToString(_listCoupon.value)
        return withContext(Dispatchers.IO) {
            URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
        }
    }

    suspend fun handleDataNextPage(onSuccess: (data: String) -> Unit) {
        onSuccess(handleData())
        clearList()
    }

    private fun playBeep(context: Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.beep)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }
}