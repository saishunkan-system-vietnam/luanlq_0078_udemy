package com.example.qrgrant.ui.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.qrgrant.R
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun CustomRadioButton(title: String, value: Boolean, colorText: Color, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
    ) {
        RadioButton(
            selected = value, onClick = onClick, colors = RadioButtonDefaults.colors(
                selectedColor = colorResource(R.color.primary),
                unselectedColor = colorResource(R.color.primary),
            )
        )
        Text(title, style = PrimaryStyle.medium(15, colorText))
    }
}