package com.example.qrgrant.ui.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun RichText(
    parts: List<Pair<String, SpanStyle>>,
    fontSize: TextUnit = 15.sp,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        buildAnnotatedString {
            for ((text, style) in parts) {
                withStyle(style = style) {
                    append(text)
                }
            }
        },
        fontSize = fontSize,
        textAlign = textAlign,
    )
}