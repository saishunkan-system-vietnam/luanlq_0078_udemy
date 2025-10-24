package com.example.qrgrant.ui.screen.rule.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.qrgrant.ui.theme.PrimaryStyle


@Composable
fun SectionItem(title: String, content: String) {
    Text(title, style = PrimaryStyle.medium(18))
    Spacer(Modifier.height(5.dp))
    Text(content, style = PrimaryStyle.normal(15))
    Spacer(Modifier.height(30.dp))
}
