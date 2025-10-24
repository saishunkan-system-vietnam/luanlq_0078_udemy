package com.example.qrgrant.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.text.RichText

@Composable
fun ItemListCoupons(
    code: String, date: String = "", indexList: Int, color: Color = colorResource(R.color.body_text)
) {
    if (indexList != 0) Spacer(modifier = Modifier.padding(top = 5.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, colorResource(R.color.gray_color), RoundedCornerShape(0))
            .padding(10.dp)
    ) {
        RichText(
            listOf(
                "${indexList + 1}".padStart(4, '0') + ": " + code to SpanStyle(
                    color, 16.sp, fontWeight = FontWeight.W400
                ),
                if (date.isNotEmpty()) date to SpanStyle(
                    color, 14.sp, fontWeight = FontWeight.W400
                ) else "" to SpanStyle(),
            ),
        )
    }
}