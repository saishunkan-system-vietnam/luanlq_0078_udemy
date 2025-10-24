package com.example.qrgrant.ui.screen.search_history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.screen.qr_scanner.QrScannerScreen
import com.example.qrgrant.ui.navigation.AppErrorState
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offUntil
import com.example.qrgrant.ui.screen.search_history.view_model.HistoryScanViewModel
import com.example.qrgrant.ui.screen.search_history.view_model.SearchHistoryViewModel
import kotlinx.coroutines.launch

@Composable
fun HistoryScanScreen(navController: NavController, appErrorState: AppErrorState) {
    val backStackEntry = navController.getBackStackEntry(Routes.SEARCH_HISTORY)
    val searchHistoryViewModel: SearchHistoryViewModel = viewModel(backStackEntry)
    val viewModel: HistoryScanViewModel = viewModel()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val scannerEnabled by viewModel.scannerEnabled.collectAsState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            CustomAppbar(
                "クーポン読み取り",
                navController,
                backGroundColor = R.color.green_color
            )
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            QrScannerScreen(
                enabled = scannerEnabled,
                onScanned = { value ->
                    coroutineScope.launch {
                        viewModel.handleDataQr(
                            context,
                            appErrorState,
                            searchHistoryViewModel,
                            value
                        ) { data ->
                            navController.offUntil(Routes.HISTORY_DETAIL + "/$data")
                        }
                    }
                })
        }
    }
}