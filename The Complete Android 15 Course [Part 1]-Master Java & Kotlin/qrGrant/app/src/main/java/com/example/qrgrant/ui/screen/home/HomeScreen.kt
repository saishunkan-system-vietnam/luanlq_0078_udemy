package com.example.qrgrant.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAlertDialog
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.next
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.screen.home.components.ItemHome
import com.example.qrgrant.ui.screen.home.components.SideBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val viewModel: HomeViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val isLoading by viewModel.isLoading.collectAsState()
    val showDialog by viewModel.showDialog.collectAsState()

    if (showDialog) {
        CustomAlertDialog(title = "ログアウト",
            message = "ログアウトしてもよろしいですか？",
            dismissText = "キャンセル",
            titleAlign = TextAlign.Center,
            messageAlign = TextAlign.Center,
            confirmTextColor = Color.Red,
            dismissTextColor = Color.Blue,
            onConfirm = {
                viewModel.logout()
                navController.offAll(Routes.LOGIN)
            },
            onDismiss = {
                viewModel.closeDialog()
            })
    }

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = { SideBar(navController) }) {
        Scaffold(containerColor = Color.White, topBar = {
            CustomAppbar("メインメニュー",
                navController,
                isBack = false,
                hasDrawer = true,
                handlerDrawer = { scope.launch { drawerState.open() } })
        }, content = { paddingValues ->
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = colorResource(R.color.primary))
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                ) {

                    ItemHome(
                        text1 = "地域クーポンの",
                        text2 = "補助金申請",
                        text3 = "を行います",
                        textButton = "補助金申請",
                        buttonColor = colorResource(R.color.primary)
                    ) { navController.next(Routes.APPLY) }

                    ItemHome(
                        text1 = "補助金申請の",
                        text2 = "履歴",
                        text3 = "を確認します",
                        textButton = "履歴確認",
                        buttonColor = colorResource(R.color.green_color)
                    ) { navController.next(Routes.SEARCH_HISTORY) }

                    ItemHome(
                        text1 = "補助金申請の",
                        text2 = "精算状況",
                        text3 = "を確認します",
                        textButton = "精算状況確認",
                        buttonColor = colorResource(R.color.orange_color)
                    ) { navController.next(Routes.BATCH) }

                    ItemHome(
                        text1 = "補助金申請の",
                        text2 = "取り消し",
                        text3 = "を行います",
                        textButton = "取り消し",
                        buttonColor = colorResource(R.color.red_color)
                    ) { navController.next(Routes.CANCEL) }
                }
            }
        })
    }

}