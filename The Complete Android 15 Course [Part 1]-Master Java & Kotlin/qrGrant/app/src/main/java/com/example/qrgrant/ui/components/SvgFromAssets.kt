package com.example.qrgrant.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder

@Composable
fun SvgFromAssets(fileName: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = "file:///android_asset/$fileName",
        imageLoader = coil.ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
    )
}
