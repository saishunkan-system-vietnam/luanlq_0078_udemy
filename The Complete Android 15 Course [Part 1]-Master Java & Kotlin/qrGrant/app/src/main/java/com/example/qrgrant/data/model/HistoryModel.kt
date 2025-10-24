package com.example.qrgrant.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.qrgrant.R
import kotlinx.serialization.Serializable

@Serializable
data class HistoryModel(
    val code: String? = null,
    val qrcode: String? = null,
    val status: String? = null,
    val id: Int? = null,
    val receiveDatetime: String? = null,
    val paymentBatchTarget: Int? = null,
    val paymentBatchResultId: Int? = null,
    val amount: Int? = null,
    val coupons: List<Coupon> = emptyList()
)

@Serializable
data class Coupon(
    val qrcode: String? = null,
    val code: String? = null,
    val amount: Int? = null,
    val status: String? = null
)

@Composable
fun HistoryModel.statusColor(): Color {
    return colorResource(if (status == "cancel") R.color.red_color else R.color.body_text)
}