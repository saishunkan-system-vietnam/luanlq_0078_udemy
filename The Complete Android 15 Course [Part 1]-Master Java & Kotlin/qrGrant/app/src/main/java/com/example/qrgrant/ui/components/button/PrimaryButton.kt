package com.example.qrgrant.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.SvgFromAssets
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    width: Dp = 150.dp,
    height: Dp = 50.dp,
    radius: Dp = 50.dp,
    fontSize: Int = 16,
    hasIcon: Boolean = false,
    isLoading: Boolean = false,
    style: TextStyle = PrimaryStyle.medium(fontSize),
    backGroundColor: Color = colorResource(R.color.primary),
    textColor: Color = Color.White,
    borderColor: Color? = null,
    onClick: (() -> Unit)? = null
) {
    val isDisable = onClick == null
    val defaultBorder = ButtonDefaults.outlinedButtonBorder(enabled = !isDisable)

    Button(onClick = { onClick?.invoke() },
        modifier = modifier.size(width, height),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isDisable) colorResource(R.color.gray_color).copy(
                .3f
            ) else backGroundColor,
            contentColor = if (isDisable) colorResource(R.color.gray_color) else textColor
        ),
        shape = RoundedCornerShape(radius),
        contentPadding = PaddingValues(0.dp),
        border = borderColor?.let { BorderStroke(defaultBorder.width, SolidColor(it)) }) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White)
        } else {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                if (hasIcon) {
                    SvgFromAssets(
                        "icon_confirm.svg",
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 20.dp)
                            .size(16.dp)
                    )
                }

                Text(text = text, style = style)
            }

        }
    }
}
