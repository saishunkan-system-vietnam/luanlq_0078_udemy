package com.example.qrgrant.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrgrant.ui.components.text.RichText
import com.example.qrgrant.ui.components.button.PrimaryButton

@Composable
fun ItemHome(
    textButton: String,
    text1: String,
    text2: String,
    text3: String,
    buttonColor: Color,
    onClick: () -> Unit
) {


    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(buttonColor.copy(alpha = .1f), shape = RoundedCornerShape(10.dp))
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        RichText(
            listOf(
                "${text1}\n" to SpanStyle(Color.Black, 15.sp),
                text2 to SpanStyle(buttonColor, 15.sp, fontWeight = FontWeight.Bold),
                text3 to SpanStyle(Color.Black, 15.sp)
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        PrimaryButton(
            textButton,
            width = 200.dp,
            height = 55.dp,
            radius = 10.dp,
            fontSize = 17,
            backGroundColor = buttonColor,
            onClick = onClick
        )
    }
}