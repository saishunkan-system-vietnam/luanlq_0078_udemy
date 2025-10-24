package com.example.qrgrant.ui.screen.search_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.data.model.HistoryModel
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.CustomDropdown
import com.example.qrgrant.ui.screen.search_history.components.ItemDetail
import com.example.qrgrant.ui.screen.search_history.view_model.HistoryDetailViewModel
import com.example.qrgrant.utils.Utils

@Composable
fun HistoryDetailScreen(navController: NavController, listHistory: List<HistoryModel>) {
    val viewModel: HistoryDetailViewModel = viewModel()
    val showCoupons by viewModel.showCoupons.collectAsState()
    val totalCoupon = listHistory.size.toString()
    val sumAmount = listHistory.sumOf { it.amount ?: 0 }.toString()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomAppbar(
                "申請詳細", navController, backGroundColor = R.color.green_color
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Column {
                Column(
                    modifier = Modifier
                        .background(colorResource(R.color.gray_color).copy(.2f))
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    ItemDetail("申請枚数", "${Utils.formatNumber(totalCoupon)}枚")
                    ItemDetail("申請額", "${Utils.formatNumber(sumAmount)}枚")
                }

                Spacer(modifier = Modifier.height(10.dp))
                CustomDropdown(
                    showCoupons,
                    titleDropBox = "地域クーポン一覧",
                    listHistory = listHistory
                ) { viewModel.handleShowCoupons() }
            }
        }
    }
}