package com.example.qrgrant.ui.screen.search_history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.button.CustomTabBar
import com.example.qrgrant.ui.navigation.AppErrorState
import com.example.qrgrant.ui.screen.search_history.components.body.BodySearch
import com.example.qrgrant.ui.screen.search_history.components.body.BodySearchToday
import com.example.qrgrant.ui.screen.search_history.view_model.SearchHistoryViewModel

@Composable
fun SearchHistoryScreen(navController: NavController, appErrorState: AppErrorState) {
    val viewModel: SearchHistoryViewModel = viewModel()
    val context = androidx.compose.ui.platform.LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadData(context = context)
    }

    Scaffold(
        containerColor = Color.White,
        topBar = { CustomAppbar("利用規約", navController, backGroundColor = R.color.green_color) },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            CustomTabBar(
                titles = listOf("今日", "詳細検索"),
                contents = listOf(
                    { BodySearchToday(navController, viewModel) },
                    { BodySearch(navController, viewModel, appErrorState) },
                ),
            )
        }
    }
}