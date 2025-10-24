package com.example.qrgrant.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    confirmText: String = "OK",
    dismissText: String? = null,
    confirmTextColor: Color = Color.Blue,
    dismissTextColor: Color = Color.Gray,
    onConfirm: () -> Unit,
    onDismiss: (() -> Unit)? = null,
    width: Dp = 300.dp,
    height: Dp = Dp.Unspecified,
    cornerRadius: Dp = 16.dp,
    dismissOnClickOutside: Boolean = false,
    titleAlign: TextAlign = TextAlign.Start,
    messageAlign: TextAlign = TextAlign.Start,
    contentPadding: Dp = 16.dp
) {
    Dialog(
        onDismissRequest = { if (dismissOnClickOutside) onDismiss?.invoke() }
    ) {
        Card(
            shape = RoundedCornerShape(cornerRadius),
            modifier = Modifier
                .width(width)
                .then(
                    if (height != Dp.Unspecified) Modifier.height(height) else Modifier
                ),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.padding(contentPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = titleAlign,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    message,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = messageAlign,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    dismissText?.let {
                        TextButton(onClick = { onDismiss?.invoke() }) {
                            Text(it, color = dismissTextColor)
                        }
                    }
                    TextButton(onClick = onConfirm) {
                        Text(confirmText, color = confirmTextColor)
                    }
                }
            }
        }
    }
}
