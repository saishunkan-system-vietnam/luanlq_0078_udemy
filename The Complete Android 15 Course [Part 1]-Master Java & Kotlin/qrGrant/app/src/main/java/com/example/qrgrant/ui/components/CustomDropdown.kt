package com.example.qrgrant.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.qrgrant.R
import com.example.qrgrant.data.model.Coupon
import com.example.qrgrant.data.model.HistoryModel
import com.example.qrgrant.data.model.statusColor
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.theme.PrimaryStyle
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CustomDropdown(
    isShow: Boolean,
    titleQuantity: String = "",
    titleDropBox: String = "",
    coupons: List<Coupon> = emptyList(),
    listHistory: List<HistoryModel> = emptyList(),
    handleShowCoupons: () -> Unit,
) {
    val paddingDropdown = if (titleQuantity.isNotEmpty()) 50.dp else 20.dp

    Column {
        if (titleQuantity.isNotEmpty()) Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                titleQuantity, style = PrimaryStyle.regular(
                    15, colorResource(R.color.body_text)
                )
            )

            if (titleDropBox.isEmpty()) PrimaryButton(
                if (isShow) "明細を非表示" else "明細を表示",
                width = 80.dp,
                height = 27.dp,
                backGroundColor = colorResource(if (coupons.isEmpty()) R.color.green_color else R.color.primary),
                style = PrimaryStyle.regular(10, color = Color.White)
            ) { handleShowCoupons() }
        }

        if (titleDropBox.isNotEmpty()) Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .padding(horizontal = paddingDropdown),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                titleDropBox, style = PrimaryStyle.regular(
                    15, colorResource(R.color.body_text)
                )
            )

            PrimaryButton(
                if (isShow) "明細を非表示" else "明細を表示",
                width = 80.dp,
                height = 27.dp,
                backGroundColor = colorResource(if (coupons.isEmpty()) R.color.green_color else R.color.primary),
                style = PrimaryStyle.regular(10, color = Color.White)
            ) { handleShowCoupons() }
        }
        if (isShow) LazyColumn(
            modifier = Modifier
                .then(
                    if (coupons.isNotEmpty()) Modifier.height(300.dp) else Modifier
                )
                .fillMaxWidth()
                .padding(horizontal = paddingDropdown),
        ) {
            items(if (coupons.isEmpty()) listHistory.size else coupons.size) { index ->
                val item = (coupons.ifEmpty { listHistory })[index]

                when (item) {
                    is Coupon -> {
                        ItemListCoupons(item.code ?: "", indexList = index)
                    }

                    is HistoryModel -> {
                        ItemListCoupons(
                            item.code ?: "", " (${
                                LocalDateTime.parse(
                                    item.receiveDatetime ?: "",
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                                ).format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                            })", index, item.statusColor()
                        )
                    }
                }
            }
        }
    }
}