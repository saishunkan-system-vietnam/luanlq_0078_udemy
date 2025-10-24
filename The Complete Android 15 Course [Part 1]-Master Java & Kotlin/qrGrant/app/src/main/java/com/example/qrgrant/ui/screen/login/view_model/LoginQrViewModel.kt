package com.example.qrgrant.ui.screen.login.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.example.qrgrant.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginQrViewModel : ViewModel() {
    private val _storage = com.example.qrgrant.utils.LocalStorage

    private val _scannerEnabled = MutableStateFlow(true)
    val scannerEnabled: StateFlow<Boolean> = _scannerEnabled
    private val _count = MutableStateFlow(0)

    private fun stopScanner() {
        _scannerEnabled.value = false
    }

    fun handleDataQr(context: Context, onSuccess: () -> Unit) {
        _count.value++

        if (_count.value > 1) return
        stopScanner()
        playBeep(context)

        _storage.setData("isLogin", "true", "Boolean")
        onSuccess()
    }


    private fun playBeep(context: Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.beep)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }
}
