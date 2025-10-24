package com.example.qrgrant.ui.screen.search_history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.qrgrant.R
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun ItemDetail(title: String, value: String) {
    Column {
        Row(modifier = Modifier.fillMaxWidth().padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, style = PrimaryStyle.regular(12))
            Text(value, style = PrimaryStyle.medium())
        }
        HorizontalDivider(color = colorResource(R.color.border_color), thickness = 1.dp)
    }
}