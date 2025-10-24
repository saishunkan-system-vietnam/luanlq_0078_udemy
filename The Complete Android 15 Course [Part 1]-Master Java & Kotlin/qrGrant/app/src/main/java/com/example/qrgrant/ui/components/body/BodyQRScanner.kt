package com.example.qrgrant.ui.components.body

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.text.RichText
import com.example.qrgrant.ui.screen.qr_scanner.QrScannerScreen
import kotlinx.coroutines.launch

@Composable
fun BodyQRScanner(
    scannerEnabled: Boolean,
    count: Int,
    handleScanner: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        QrScannerScreen(height = screenHeight * .5f,
            enabled = scannerEnabled,
            onScanned = { value -> coroutineScope.launch { handleScanner(value) } })
        Box(contentAlignment = Alignment.Center) {
            RichText(
                listOf(
                    "$count" to SpanStyle(
                        colorResource(R.color.link_color), 48.sp, fontWeight = FontWeight.Bold
                    ),
                    "  枚" to SpanStyle(
                        colorResource(R.color.body_text), 15.sp, fontWeight = FontWeight.W400
                    ),
                ),
            )
        }
    }
}