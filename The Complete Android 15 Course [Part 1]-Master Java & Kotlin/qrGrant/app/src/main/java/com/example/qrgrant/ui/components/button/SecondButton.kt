package com.example.qrgrant.ui.components.button

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.qrgrant.R

@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    width: Dp = 150.dp,
    height: Dp = 55.dp,
    radius: Dp = 10.dp,
    borderColor: Color = colorResource(R.color.primary),
    textColor: Color = colorResource(R.color.primary),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(width, height)
            .border(2.dp, borderColor, RoundedCornerShape(radius)),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(color = textColor)
        )
    }
}
