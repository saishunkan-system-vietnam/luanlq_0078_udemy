package com.example.qrgrant.ui.screen.apply.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.qrgrant.R
import com.example.qrgrant.data.model.Coupon
import com.example.qrgrant.ui.navigation.AppErrorState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class ApplyConfirmViewModel : ViewModel() {
    private val _listCoupon = MutableStateFlow<List<Coupon>>(emptyList())
    private val _listFail = MutableStateFlow<List<Coupon>>(emptyList())
    val listCoupon: StateFlow<List<Coupon>> = _listCoupon

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    suspend fun loadData(
        context: Context, appErrorState: AppErrorState? = null,
        coupons: List<String>
    ) {
        _isLoading.value = true
        val json =
            context.resources.openRawResource(R.raw.coupons).bufferedReader().use { it.readText() }

        val gson = Gson()

        val type = object : TypeToken<List<Coupon>>() {}.type
        val list: List<Coupon> = gson.fromJson(json, type)

        val matched = list.filter { coupon -> coupons.contains(coupon.qrcode) }

        val notFound = coupons.filter { code -> list.none { it.qrcode == code } }
            .map { Coupon(qrcode = it, code = it) }

        _listCoupon.value = matched
        _listFail.value = notFound

        delay(500)
        _isLoading.value = false

        if (_listFail.value.isNotEmpty()) appErrorState?.show(handleShowPopup(), false)
    }

    private fun handleShowPopup(): String {
        var fail = "存在しないクーポン：${_listFail.value.size}枚\n"
        for (data in _listFail.value) {
            fail += "${data.qrcode}\n"
        }
        return fail
    }

    private suspend fun handleData(): String {
        val json = Json.encodeToString(_listCoupon.value)
        return withContext(Dispatchers.IO) {
            URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
        }
    }

    suspend fun handleDataNextPage(onSuccess: (data: String) -> Unit) {
        onSuccess(handleData())
    }
}