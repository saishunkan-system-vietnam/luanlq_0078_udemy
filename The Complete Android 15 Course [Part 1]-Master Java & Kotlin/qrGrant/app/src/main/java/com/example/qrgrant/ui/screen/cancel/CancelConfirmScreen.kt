package com.example.qrgrant.ui.screen.cancel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.CustomDropdown
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.navigation.AppErrorState
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.screen.cancel.view_model.CancelConfirmViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun CancelConfirmScreen(
    navController: NavController, appErrorState: AppErrorState? = null,
    coupons: List<String>
) {
    val viewModel: CancelConfirmViewModel = viewModel()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val showCoupons by viewModel.showCoupons.collectAsState()
    val listApplied by viewModel.listApplied.collectAsState()
    val listCanceled by viewModel.listCanceled.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData(context, appErrorState, coupons)
    }

    Scaffold(containerColor = Color.White, topBar = {
        CustomAppbar("内容確認", navController, backGroundColor = R.color.red_color)
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(vertical = 10.dp)
        ) {
            Text(
                "${listApplied.size + listCanceled.size}枚",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = PrimaryStyle.medium(20, colorResource(R.color.link_color))
            )
            Text(
                "読み取りました",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = PrimaryStyle.regular(15)
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (listApplied.isNotEmpty()) CustomDropdown(
                showCoupons[0], "●取消対象 : ${listApplied.size}枚", coupons = listApplied
            ) { viewModel.handleShowCoupons(0) }

            if (listCanceled.isNotEmpty()) CustomDropdown(
                showCoupons[1],
                "●取消対象外 : ${listCanceled.size}枚",
                coupons = listCanceled
            ) { viewModel.handleShowCoupons(1) }

            Spacer(modifier = Modifier.height(20.dp))

            PrimaryButton("申請取り消し",
                hasIcon = true,
                width = 200.dp,
                style = PrimaryStyle.bold(16),
                backGroundColor = colorResource(R.color.red_color),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { navController.offAll(Routes.CANCEL_DONE) })
        }
        Spacer(modifier = Modifier.height(10.dp))
    })
}