package com.example.qrgrant.ui.screen.cancel.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.qrgrant.R
import com.example.qrgrant.data.model.Coupon
import com.example.qrgrant.ui.navigation.AppErrorState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CancelConfirmViewModel : ViewModel() {
    private val _listFail = MutableStateFlow<List<Coupon>>(emptyList())
    private val _listApplied = MutableStateFlow<List<Coupon>>(emptyList())
    val listApplied: StateFlow<List<Coupon>> = _listApplied
    private val _listCanceled = MutableStateFlow<List<Coupon>>(emptyList())
    val listCanceled: StateFlow<List<Coupon>> = _listCanceled

    private val _showCoupons = MutableStateFlow(List(2) { true })
    val showCoupons: StateFlow<List<Boolean>> = _showCoupons

    suspend fun loadData(
        context: Context, appErrorState: AppErrorState? = null, coupons: List<String>
    ) {
        val json =
            context.resources.openRawResource(R.raw.coupons).bufferedReader().use { it.readText() }

        val gson = Gson()

        val type = object : TypeToken<List<Coupon>>() {}.type
        val list: List<Coupon> = gson.fromJson(json, type)

        val matched = list.filter { coupon -> coupons.contains(coupon.qrcode) }
        _listFail.value = coupons.filter { code -> list.none { it.qrcode == code } }
            .map { Coupon(qrcode = it, code = it) }

        val applied = matched.filter { coupon -> coupon.status == "unused" }
        val canceled = matched.filter { coupon -> coupon.status == "used" }

        _listApplied.value = applied
        _listCanceled.value = canceled

        delay(500)
        if (_listFail.value.isNotEmpty()) appErrorState?.show(handleShowPopup(), false)
    }

    private fun handleShowPopup(): String {
        var fail = "存在しないクーポン：${_listFail.value.size}枚\n"
        for (data in _listFail.value) {
            fail += "${data.qrcode}\n"
        }
        return fail
    }

    fun handleShowCoupons(index: Int) {
        _showCoupons.value = _showCoupons.value.toMutableList().also {
            it[index] = !it[index]
        }
    }
}