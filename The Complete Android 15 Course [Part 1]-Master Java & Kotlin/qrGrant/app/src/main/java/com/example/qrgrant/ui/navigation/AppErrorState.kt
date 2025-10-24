package com.example.qrgrant.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AppErrorState {
    var show by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var message by mutableStateOf("")
    var onDismiss by mutableStateOf({})

    fun show(message: String, isError: Boolean = true, onDismiss: () -> Unit = {}) {
        this.message = message
        this.show = true
        this.isError = isError
        this.onDismiss = onDismiss
    }

    fun dismiss() {
        this.show = false
        this.message = ""
        onDismiss()
        this.onDismiss = {}
    }
}
