package com.example.qrgrant.ui.screen.qr_scanner

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScannerOverlay(
    modifier: Modifier = Modifier,
    frameSize: Dp = 250.dp,
    cornerLength: Dp = 40.dp,
    strokeWidth: Dp = 6.dp
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(frameSize)) {
            val corner = cornerLength.toPx()
            val w = size.width
            val h = size.height

            drawLine(Color.White, start = Offset(0f, 0f), end = Offset(corner, 0f), strokeWidth.toPx(), cap = StrokeCap.Round)
            drawLine(Color.White, start = Offset(0f, 0f), end = Offset(0f, corner), strokeWidth.toPx(), cap = StrokeCap.Round)

            drawLine(Color.White, start = Offset(w, 0f), end = Offset(w - corner, 0f), strokeWidth.toPx(), cap = StrokeCap.Round)
            drawLine(Color.White, start = Offset(w, 0f), end = Offset(w, corner), strokeWidth.toPx(), cap = StrokeCap.Round)

            drawLine(Color.White, start = Offset(0f, h), end = Offset(corner, h), strokeWidth.toPx(), cap = StrokeCap.Round)
            drawLine(Color.White, start = Offset(0f, h), end = Offset(0f, h - corner), strokeWidth.toPx(), cap = StrokeCap.Round)

            drawLine(Color.White, start = Offset(w, h), end = Offset(w - corner, h), strokeWidth.toPx(), cap = StrokeCap.Round)
            drawLine(Color.White, start = Offset(w, h), end = Offset(w, h - corner), strokeWidth.toPx(), cap = StrokeCap.Round)
        }
    }
}
