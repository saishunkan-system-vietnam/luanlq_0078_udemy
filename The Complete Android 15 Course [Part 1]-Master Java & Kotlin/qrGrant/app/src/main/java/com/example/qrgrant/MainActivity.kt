package com.example.qrgrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.qrgrant.ui.navigation.AppNavGraph
import com.example.qrgrant.ui.theme.QrGrantTheme
import com.example.qrgrant.utils.LocalStorage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalStorage.init(this)
        enableEdgeToEdge()
        setContent {
            QrGrantTheme {
                val navController = rememberNavController()
                val focusManager = LocalFocusManager.current

                CompositionLocalProvider(
                    LocalNavController provides navController
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) { focusManager.clearFocus() }
                    ) {
                        AppNavGraph(navController)
                    }
                }
            }
        }
    }
}

val LocalNavController = staticCompositionLocalOf<NavController> { error("LocalContext") }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QrGrantTheme {}
}