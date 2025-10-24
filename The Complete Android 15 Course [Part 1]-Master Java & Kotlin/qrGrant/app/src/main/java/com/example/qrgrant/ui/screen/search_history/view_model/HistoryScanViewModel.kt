package com.example.qrgrant.ui.screen.search_history.view_model

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrgrant.R
import com.example.qrgrant.ui.navigation.AppErrorState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryScanViewModel : ViewModel() {
    private val _scannerEnabled = MutableStateFlow(true)
    val scannerEnabled: StateFlow<Boolean> = _scannerEnabled

    private val _count = MutableStateFlow(0)

    private fun startScanner() {
        _scannerEnabled.value = true
        _count.value = 0
    }

    private fun stopScanner() {
        _scannerEnabled.value = false
    }

    suspend fun handleDataQr(
        context: Context,
        appErrorState: AppErrorState,
        viewModel: SearchHistoryViewModel,
        value: String,
        onSuccess: (data: String) -> Unit
    ) {
        _count.value++

        if (_count.value > 1) return
        stopScanner()
        playBeep(context)
        viewModel.handleSearch(value,
            appErrorState,
            onDismiss = {
                viewModelScope.launch {
                    delay(100)
                    startScanner()
                }
            },
            onSuccess = { data -> onSuccess(data) })
    }


    private fun playBeep(context: Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.beep)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }
}