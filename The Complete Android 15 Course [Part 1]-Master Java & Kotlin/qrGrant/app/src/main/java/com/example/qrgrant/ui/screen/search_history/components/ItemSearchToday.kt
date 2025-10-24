package com.example.qrgrant.ui.screen.search_history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrgrant.R
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun RowScope.ItemSearchToday(
    title: String,
    number: String,
    unit: String,
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(vertical = 13.dp)
            .padding(start = 13.dp, end = 5.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {
        Text(title, style = PrimaryStyle.medium(15))
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            BasicText(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = colorResource(R.color.link_color),
                            fontWeight = FontWeight.Medium
                        )
                    ) { append(number) }

                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp, fontWeight = FontWeight.W400
                        )
                    ) { append(" $unit") }
                },
                autoSize = TextAutoSize.StepBased(maxFontSize = 30.sp, minFontSize = 1.sp),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Clip,
            )
        }
    }
}

