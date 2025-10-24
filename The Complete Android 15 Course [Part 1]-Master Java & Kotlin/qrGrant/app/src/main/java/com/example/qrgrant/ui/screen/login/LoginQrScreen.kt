package com.example.qrgrant.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.screen.qr_scanner.QrScannerScreen
import com.example.qrgrant.ui.components.button.SecondaryButton
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.navigation.offUntil
import com.example.qrgrant.ui.screen.login.view_model.LoginQrViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle
import kotlinx.coroutines.launch

@Composable
fun LoginQrScreen(navController: NavController) {
    val viewModel: LoginQrViewModel = viewModel()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val scannerEnabled by viewModel.scannerEnabled.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color.White,
        topBar = { CustomAppbar("QRコードログイン", navController) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ログイン用QRコード", style = PrimaryStyle.medium(
                        20, color = colorResource(R.color.link_color)
                    ), modifier = Modifier.padding(vertical = 5.dp)
                )

                Text(
                    text = "を読み取ってください", style = PrimaryStyle.regular(15)
                )
            }

            QrScannerScreen(
                height = screenHeight * 0.7f,
                enabled = scannerEnabled,
                onScanned = {
                    coroutineScope.launch {
                        viewModel.handleDataQr(context) {
                            navController.offAll(Routes.HOME)
                        }
                    }
                })

            SecondaryButton(
                "ポータルアカウントを\nご利用の場合はこちら",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = 40.dp)
            ) {
                navController.offUntil(Routes.LOGIN_ACCOUNT)
            }
        }
    }
}
