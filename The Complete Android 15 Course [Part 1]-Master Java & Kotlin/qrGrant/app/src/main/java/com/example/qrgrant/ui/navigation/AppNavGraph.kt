package com.example.qrgrant.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.qrgrant.data.model.Coupon
import com.example.qrgrant.data.model.HistoryModel
import com.example.qrgrant.ui.components.AppErrorDialog
import com.example.qrgrant.ui.screen.apply.ApplyConfirmScreen
import com.example.qrgrant.ui.screen.apply.ApplyDoneScreen
import com.example.qrgrant.ui.screen.apply.ApplyScreen
import com.example.qrgrant.ui.screen.batch.BatchScreen
import com.example.qrgrant.ui.screen.cancel.CancelConfirmScreen
import com.example.qrgrant.ui.screen.cancel.CancelDoneScreen
import com.example.qrgrant.ui.screen.cancel.CancelScreen
import com.example.qrgrant.ui.screen.home.HomeScreen
import com.example.qrgrant.ui.screen.license.LicenseScreen
import com.example.qrgrant.ui.screen.login.LoginAccountScreen
import com.example.qrgrant.ui.screen.login.LoginQrScreen
import com.example.qrgrant.ui.screen.login.LoginScreen
import com.example.qrgrant.ui.screen.rule.RuleScreen
import com.example.qrgrant.ui.screen.search_history.HistoryDetailScreen
import com.example.qrgrant.ui.screen.search_history.HistoryScanScreen
import com.example.qrgrant.ui.screen.search_history.SearchHistoryScreen
import com.example.qrgrant.ui.screen.splash.SplashScreen
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavGraph(navController: NavHostController) {
    val appViewModel: AppViewModel = viewModel()
    val appErrorState = appViewModel.appErrorState

    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }
        composable(Routes.HOME) {
            HomeScreen(navController)
        }
        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }
        composable(Routes.LOGIN_ACCOUNT) {
            LoginAccountScreen(navController)
        }
        composable(Routes.LOGIN_QR) {
            LoginQrScreen(navController)
        }
        composable(Routes.LICENSE) {
            LicenseScreen(navController)
        }
        composable(Routes.RULE) {
            RuleScreen(navController)
        }
        composable(Routes.APPLY) {
            ApplyScreen(navController)
        }
        composable(Routes.APPLY_CONFIRM + "/{data}") { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("data") ?: return@composable
            val decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
            val list = Json.decodeFromString<List<String>>(decoded)

            ApplyConfirmScreen(navController, appErrorState, list)
        }
        composable(Routes.APPLY_DONE + "/{data}") { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("data") ?: return@composable
            val decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
            val list = Json.decodeFromString<List<Coupon>>(decoded)

            ApplyDoneScreen(navController, list)
        }
        composable(Routes.SEARCH_HISTORY) {
            SearchHistoryScreen(navController, appErrorState)
        }
        composable(Routes.HISTORY_SCAN) {
            HistoryScanScreen(navController, appErrorState)
        }
        composable(Routes.HISTORY_DETAIL + "/{data}") { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("data") ?: return@composable
            val decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
            val list = Json.decodeFromString<List<HistoryModel>>(decoded)

            HistoryDetailScreen(navController, list)
        }
        composable(Routes.BATCH) {
            BatchScreen(navController)
        }
        composable(Routes.CANCEL) {
            CancelScreen(navController)
        }
        composable(Routes.CANCEL_CONFIRM + "/{data}") { backStackEntry ->
            val encoded = backStackEntry.arguments?.getString("data") ?: return@composable
            val decoded = URLDecoder.decode(encoded, StandardCharsets.UTF_8.toString())
            val list = Json.decodeFromString<List<String>>(decoded)

            CancelConfirmScreen(navController, appErrorState, list)
        }
        composable(Routes.CANCEL_DONE) {
            CancelDoneScreen(navController)
        }
    }

    AppErrorDialog(show = appErrorState.show,
        isError = appErrorState.isError,
        message = appErrorState.message,
        onDismiss = { appErrorState.dismiss() })
}
