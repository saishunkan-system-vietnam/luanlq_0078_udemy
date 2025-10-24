package com.example.qrgrant.ui.screen.login.view_model

import androidx.lifecycle.ViewModel
import com.example.qrgrant.utils.LocalStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginAccountViewModel : ViewModel() {
    private val _storage = LocalStorage

    val email = MutableStateFlow("")
    val pass = MutableStateFlow("")

    private val _errors = MutableStateFlow(listOf("", ""))
    val errors: StateFlow<List<String>> = _errors

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun handleLogin(onSuccess: () -> Unit) {
        if (isValidate()) return
        _isLoading.value = true
        delay(500)
        _isLoading.value = false

        _storage.setData("isLogin", "true", "Boolean")
        onSuccess()
    }

    private fun isValidate(): Boolean {
        var result = false
        val newErrors = mutableListOf("", "")

        if (email.value.isEmpty()) {
            newErrors[0] = "メールアドレスを入力してください"
            result = true
        }
        if (pass.value.isEmpty()) {
            newErrors[1] = "パスワードを入力してください"
            result = true
        }

        _errors.value = newErrors
        return result
    }
}
