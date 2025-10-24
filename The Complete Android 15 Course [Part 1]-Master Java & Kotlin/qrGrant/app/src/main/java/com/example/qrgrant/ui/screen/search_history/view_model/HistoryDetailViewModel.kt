package com.example.qrgrant.ui.screen.search_history.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HistoryDetailViewModel : ViewModel() {
    private val _showCoupons = MutableStateFlow(true)
    val showCoupons: StateFlow<Boolean> = _showCoupons

    fun handleShowCoupons() {
        _showCoupons.value = !_showCoupons.value
    }

}