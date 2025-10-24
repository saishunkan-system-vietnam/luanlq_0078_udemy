package com.example.qrgrant.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun AppErrorDialog(
    show: Boolean, message: String, isError: Boolean = true, onDismiss: () -> Unit
) {
    if (show) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(if (isError) R.color.red_color else R.color.orange_color))
                    .padding(vertical = 24.dp, horizontal = 16.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = message,
                        style = PrimaryStyle.regular(16, Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp)
                            .align(if (isError) Alignment.Start else Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    PrimaryButton(
                        "OK",
                        radius = 5.dp,
                        width = 100.dp,
                        height = 45.dp,
                        onClick = onDismiss,
                        borderColor = Color.White,
                        modifier = Modifier.align(Alignment.End),
                        backGroundColor = colorResource(if (isError) R.color.red_color else R.color.orange_color)
                    )
                }
            }
        }
    }
}
