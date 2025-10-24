package com.example.qrgrant.ui.screen.search_history.components.body

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.next
import com.example.qrgrant.ui.screen.search_history.view_model.SearchHistoryViewModel
import com.example.qrgrant.ui.screen.search_history.components.ItemSearchToday
import com.example.qrgrant.ui.theme.PrimaryStyle
import com.example.qrgrant.utils.Utils
import kotlinx.coroutines.launch

@Composable
fun BodySearchToday(navController: NavController, viewModel: SearchHistoryViewModel) {
    val coroutineScope = rememberCoroutineScope()

    val totalCoupon by viewModel.totalCoupon.collectAsState()
    val sumAmount by viewModel.sumAmount.collectAsState()

    val isLoading by viewModel.isLoadingToday.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(viewModel.todayTime, style = PrimaryStyle.medium(15, Color(0x0ff707070)))

        Row(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .height(220.dp)
                .fillMaxSize()
                .background(Color.Gray.copy(0.1f), shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(6.dp))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemSearchToday("申請枚数", Utils.formatNumber(totalCoupon), " 枚")
            VerticalDivider(
                thickness = 1.dp,
                color = colorResource(R.color.border_color),
                modifier = Modifier.fillMaxHeight()
            )
            ItemSearchToday("申請額", Utils.formatNumber(sumAmount), " 円")
        }

        PrimaryButton(
            "詳細へ",
            width = 160.dp,
            isLoading = isLoading,
            backGroundColor = colorResource(R.color.green_color),
            onClick = if (totalCoupon != "0" && sumAmount != "0") {
                {
                    coroutineScope.launch {
                        viewModel.handleSearch(isToday = true) { data ->
                            navController.next(Routes.HISTORY_DETAIL + "/$data")
                        }
                    }
                }
            } else null)
    }
}