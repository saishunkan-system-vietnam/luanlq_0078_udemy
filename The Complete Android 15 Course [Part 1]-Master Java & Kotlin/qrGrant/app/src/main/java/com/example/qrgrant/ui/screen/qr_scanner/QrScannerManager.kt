package com.example.qrgrant.ui.screen.qr_scanner

import android.content.Context
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

object QrScannerManager {
    private var cameraProvider: ProcessCameraProvider? = null

    fun startScanner(
        context: Context,
        lifecycleOwner: LifecycleOwner,
        previewView: PreviewView,
        onScanned: (String) -> Unit
    ) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()
                .also { it.surfaceProvider = previewView.surfaceProvider }

            val scanner = BarcodeScanning.getClient()
            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build().also {
                    it.setAnalyzer(
                        ContextCompat.getMainExecutor(context)
                    ) { imageProxy ->
                        processImageProxy(scanner, imageProxy, onScanned)
                    }
                }

            cameraProvider?.unbindAll()
            cameraProvider?.bindToLifecycle(
                lifecycleOwner,
                CameraSelector.DEFAULT_BACK_CAMERA,
                preview,
                analysis
            )
        }, ContextCompat.getMainExecutor(context))
    }

    fun stopScanner() {
        cameraProvider?.unbindAll()
    }
}


@OptIn(ExperimentalGetImage::class)
private fun processImageProxy(
    scanner: com.google.mlkit.vision.barcode.BarcodeScanner,
    imageProxy: ImageProxy,
    onScanned: (String) -> Unit,
) {
    val mediaImage = imageProxy.image
    if (mediaImage != null) {
        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
        scanner.process(image).addOnSuccessListener { barcodes ->
            for (barcode in barcodes) {
                barcode.rawValue?.let { value ->
                    Log.d("Scanner", "Found QR: $value")
                    onScanned(value)
                }
            }
        }.addOnFailureListener {
            it.printStackTrace()
        }.addOnCompleteListener {
            imageProxy.close()
        }
    } else {
        imageProxy.close()
    }
}
