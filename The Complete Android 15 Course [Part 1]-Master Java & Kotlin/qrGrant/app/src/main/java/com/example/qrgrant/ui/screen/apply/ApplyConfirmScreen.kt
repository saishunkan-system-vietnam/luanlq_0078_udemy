package com.example.qrgrant.ui.screen.apply

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.navigation.AppErrorState
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.screen.apply.view_model.ApplyConfirmViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle
import kotlinx.coroutines.launch

@Composable
fun ApplyConfirmScreen(
    navController: NavController, appErrorState: AppErrorState? = null,
    coupons: List<String>
) {
    val viewModel: ApplyConfirmViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val isLoading by viewModel.isLoading.collectAsState()
    val listCoupon by viewModel.listCoupon.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData(context, appErrorState, coupons)
    }

    Scaffold(containerColor = Color.White, topBar = {
        CustomAppbar("内容確認", navController)
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Text(
                "${listCoupon.size}枚",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = PrimaryStyle.medium(20, colorResource(R.color.link_color))
            )
            Text(
                "読み取りました",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = PrimaryStyle.regular(15)
            )

            Text(
                "明細 ：",
                style = PrimaryStyle.regular(14, colorResource(R.color.body_text)),
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
            )

            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .border(1.dp, Color.Black, RoundedCornerShape(0.dp))
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center), colorResource(R.color.primary)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .border(1.dp, Color.Black, RoundedCornerShape(0.dp))
                        .padding(10.dp)
                ) {
                    items(listCoupon.size) { index ->
                        val coupon = listCoupon[index]

                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .fillMaxWidth()
                                .border(
                                    1.dp,
                                    colorResource(R.color.gray_color),
                                    RoundedCornerShape(0.dp)
                                )
                                .padding(10.dp)
                        ) {
                            Text(
                                "${index + 1}".padStart(4, '0') + " : " + coupon.code,
                                style = PrimaryStyle.regular(16)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            PrimaryButton("申請",
                hasIcon = true,
                style = PrimaryStyle.bold(16),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    coroutineScope.launch {
                        viewModel.handleDataNextPage { data -> navController.offAll(Routes.APPLY_DONE + "/$data") }
                    }
                })
        }
    })
}