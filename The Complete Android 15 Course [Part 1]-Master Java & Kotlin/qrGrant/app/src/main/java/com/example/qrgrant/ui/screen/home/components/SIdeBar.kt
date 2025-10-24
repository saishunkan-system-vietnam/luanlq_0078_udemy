package com.example.qrgrant.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.button.CustomTextButton
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.next
import com.example.qrgrant.ui.screen.home.HomeViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun SideBar(navController: NavController) {
    val viewModel: HomeViewModel = viewModel()
    val context = androidx.compose.ui.platform.LocalContext.current

    ModalDrawerSheet(
        drawerContainerColor = colorResource(R.color.primary),
        drawerShape = RoundedCornerShape(0),
        windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Top)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Text(
                    "Text",
                    modifier = Modifier.align(Alignment.Start),
                    style = PrimaryStyle.medium(16, color = Color.White)
                )
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        "ID:00000025",
                        style = PrimaryStyle.regular(16, Color.White, spacing = 1.2f)
                    )
                    Text("Test", style = PrimaryStyle.bold(16, Color.White))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CustomTextButton("プライバシーポリシー") {
                    viewModel.openWeb(context, "https://www.denso-wave.com/ja/privacy/")
                }
                CustomTextButton("ライセンス") { navController.next(Routes.LICENSE) }
                CustomTextButton("利用規約") { navController.next(Routes.RULE) }
                CustomTextButton("ログアウト") { viewModel.openDialog() }
            }
        }
    }
}
