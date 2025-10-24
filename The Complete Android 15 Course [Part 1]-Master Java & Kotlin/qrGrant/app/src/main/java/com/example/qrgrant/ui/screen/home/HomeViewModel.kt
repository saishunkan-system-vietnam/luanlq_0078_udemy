package com.example.qrgrant.ui.screen.home

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrgrant.utils.LocalStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _storage = LocalStorage

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    fun openDialog() {
        _showDialog.value = true
    }

    fun closeDialog() {
        _showDialog.value = false
    }

    private suspend fun loadData() {
        _isLoading.value = true
        delay(500)
        _isLoading.value = false
    }

    fun openWeb(context: Context, url: String) {
        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun logout() {
        closeDialog()
        _storage.setData("isLogin", "false", "Boolean")
    }
}
