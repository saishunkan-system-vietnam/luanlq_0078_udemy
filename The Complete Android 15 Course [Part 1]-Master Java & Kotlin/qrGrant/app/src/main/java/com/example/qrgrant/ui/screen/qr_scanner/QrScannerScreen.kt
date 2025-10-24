package com.example.qrgrant.ui.screen.qr_scanner

import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

@SuppressLint("RestrictedApi")
@Composable
fun QrScannerScreen(
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    enabled: Boolean,
    onScanned: (String) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var isCameraStarted by remember { mutableStateOf(false) }

    val cameraModifier = when {
        width != null && height != null -> modifier
            .width(width)
            .height(height)
        width != null -> modifier
            .width(width)
            .fillMaxHeight()
        height != null -> modifier
            .fillMaxWidth()
            .height(height)
        else -> modifier.fillMaxSize()
    }

    val previewView = remember {
        PreviewView(context).apply {
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            scaleType = PreviewView.ScaleType.FILL_CENTER
        }
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            QrScannerManager.startScanner(context, lifecycleOwner, previewView, onScanned)
            isCameraStarted = true
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(enabled) {
        if (enabled) launcher.launch(Manifest.permission.CAMERA)
        else QrScannerManager.stopScanner()
    }


    Box(
        modifier = cameraModifier,
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.matchParentSize().clipToBounds(),
            factory = { previewView },
            update = { previewView ->
                if (enabled) {
                    QrScannerManager.startScanner(
                        context = context,
                        lifecycleOwner = lifecycleOwner,
                        previewView = previewView,
                        onScanned = onScanned
                    )
                    isCameraStarted = true
                } else {
                    QrScannerManager.stopScanner()
                    isCameraStarted = false
                }
            }
        )

        if (isCameraStarted && enabled) {
            ScannerOverlay(modifier = Modifier.matchParentSize())
        }
    }
}