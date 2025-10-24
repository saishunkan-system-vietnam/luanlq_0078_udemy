package com.example.qrgrant.ui.components.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun CustomTextButton(
    text: String = "",
    content: (@Composable RowScope.() -> Unit)? = null,
    height: Dp = 55.dp,
    isRippleText: Boolean = true,
    fullWidth: Boolean = true,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Row(
        modifier = Modifier
            .then(
                if (fullWidth) Modifier.fillMaxWidth() else Modifier
            )
            .height(height)
            .clickable(
                interactionSource = interactionSource, indication = null
            ) { onClick() }, verticalAlignment = Alignment.CenterVertically
    ) {
        if (content != null) {
            content()
        } else {
            Text(
                text = text, style = PrimaryStyle.medium(
                    color = if (isPressed && isRippleText) Color.Gray.copy(
                        alpha = .5f
                    ) else Color.Black
                ), modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f), textAlign = TextAlign.Start
            )
        }
    }
}