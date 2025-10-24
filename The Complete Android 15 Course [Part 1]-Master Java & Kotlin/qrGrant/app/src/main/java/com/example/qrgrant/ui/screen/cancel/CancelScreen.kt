package com.example.qrgrant.ui.screen.cancel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.body.BodyQRScanner
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.next
import com.example.qrgrant.ui.screen.cancel.view_model.CancelViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle
import kotlinx.coroutines.launch

@Composable
fun CancelScreen(navController: NavController) {
    val viewModel: CancelViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val scannerEnabled by viewModel.scannerEnabled.collectAsState()
    val listCoupon by viewModel.listCoupon.collectAsState()

    val count = listCoupon.size

    Scaffold(containerColor = Color.White, topBar = {
        CustomAppbar("取り消し", navController, backGroundColor = R.color.red_color)
    }, content = { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text("申請を取り消す", style = PrimaryStyle.medium(20, colorResource(R.color.red_color)))
            Text("地域クーポン", style = PrimaryStyle.medium(20, colorResource(R.color.primary)))
            Text(
                "を読み取ってください\n※一度に読み取れるのは最大1,000枚です",
                textAlign = TextAlign.Center,
                style = PrimaryStyle.regular(15),
            )
            Spacer(modifier = Modifier.height(10.dp))

            BodyQRScanner(
                scannerEnabled, count
            ) { value ->
                coroutineScope.launch {
                    viewModel.handleDataQr(
                        context, value
                    ) { data -> navController.next(Routes.CANCEL_CONFIRM + "/$data") }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            PrimaryButton("確認画面へ", style = PrimaryStyle.bold(16), onClick = if (count > 0) {
                {
                    coroutineScope.launch {
                        viewModel.handleDataNextPage { data -> navController.next(Routes.CANCEL_CONFIRM + "/$data") }
                    }
                }
            } else null)
        }
    })
}