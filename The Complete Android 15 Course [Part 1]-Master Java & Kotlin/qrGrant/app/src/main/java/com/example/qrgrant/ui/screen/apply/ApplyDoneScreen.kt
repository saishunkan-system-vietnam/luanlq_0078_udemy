package com.example.qrgrant.ui.screen.apply

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.data.model.Coupon
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.CustomDropdown
import com.example.qrgrant.ui.components.SvgFromAssets
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.screen.apply.view_model.ApplyDoneViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun ApplyDoneScreen(navController: NavController, coupons: List<Coupon>) {
    val viewModel: ApplyDoneViewModel = viewModel()
    val showCoupons by viewModel.showCoupons.collectAsState()
    val listSuccess by viewModel.listSuccess.collectAsState()
    val listDisabled by viewModel.listDisabled.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.loadData(coupons)
    }


    Scaffold(containerColor = Color.White, topBar = {
        CustomAppbar("完了", navController, false)
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SvgFromAssets(
                "icon_ok.svg", modifier = Modifier.size(116.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                "申請が完了しました",
                style = PrimaryStyle.medium(20, colorResource(R.color.link_color))
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (listSuccess.isNotEmpty()) CustomDropdown(
                showCoupons[0], "●申請対象外 : ${listSuccess.size}枚", coupons = listSuccess
            ) { viewModel.handleShowCoupons(0) }

            if (listDisabled.isNotEmpty()) CustomDropdown(
                showCoupons[1],
                "●申請対象外 : ${listDisabled.size}枚",
                "存在しないクーポン : ${listDisabled.size}枚",
                coupons = listDisabled
            ) { viewModel.handleShowCoupons(1) }

            Spacer(modifier = Modifier.size(20.dp))

            PrimaryButton("メインメニューへ", width = 200.dp, style = PrimaryStyle.bold(16)) {
                navController.offAll(Routes.HOME)
            }
        }
    })
}