package com.example.qrgrant.ui.screen.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class SplashViewModel : ViewModel() {
    private val _storage = com.example.qrgrant.utils.LocalStorage

    suspend fun checkLogin(): Boolean {
        delay(500)
        return _storage.getData("isLogin", "Boolean") as? Boolean ?: false
    }
}
