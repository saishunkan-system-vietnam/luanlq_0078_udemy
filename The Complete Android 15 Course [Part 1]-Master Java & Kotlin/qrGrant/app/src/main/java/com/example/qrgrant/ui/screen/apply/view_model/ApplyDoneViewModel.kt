package com.example.qrgrant.ui.screen.apply.view_model

import androidx.lifecycle.ViewModel
import com.example.qrgrant.data.model.Coupon
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ApplyDoneViewModel : ViewModel() {
    private val _listDisabled = MutableStateFlow<List<Coupon>>(emptyList())
    val listDisabled: StateFlow<List<Coupon>> = _listDisabled
    private val _listSuccess = MutableStateFlow<List<Coupon>>(emptyList())
    val listSuccess: StateFlow<List<Coupon>> = _listSuccess

    private val _showCoupons = MutableStateFlow(List(2) { true })
    val showCoupons: StateFlow<List<Boolean>> = _showCoupons

    fun handleShowCoupons(index: Int) {
        _showCoupons.value = _showCoupons.value.toMutableList().also {
            it[index] = !it[index]
        }
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun loadData(coupons: List<Coupon>) {
        _isLoading.value = true

        val success = coupons.filter { coupon -> coupon.status == "unused" }
        val disabled = coupons.filter { coupon -> coupon.status == "used" }

        _listSuccess.value = success
        _listDisabled.value = disabled

        delay(500)
        _isLoading.value = false
    }
}