package com.example.qrgrant.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.next
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val version = packageInfo.versionName

    Scaffold(containerColor = Color.White, content = { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 10.dp), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo App",
                    modifier = Modifier.size(200.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .size(200.dp, 32.dp)
                        .border(1.dp, colorResource(R.color.primary), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "QR補助金申請App",
                        style = PrimaryStyle.regular(color = colorResource(R.color.primary))
                    )
                }

                PrimaryButton(
                    "ポータルアカウントでログイン",
                    modifier = Modifier.padding(bottom = 10.dp),
                    300.dp,
                    55.dp
                ) {
                    navController.next(Routes.LOGIN_ACCOUNT)
                }

                PrimaryButton(
                    "ログインQRコードでログイン",
                    modifier = Modifier.padding(bottom = 10.dp),
                    300.dp,
                    55.dp
                ) {
                    navController.next(Routes.LOGIN_QR)
                }

                Text(
                    "ポータルアカウントとは、補助金申請ポータルサイトのメールアドレスとパスワードになります。\n" + "ログインQRコードは、補助金申請ポータルサイトの「アプリ用ログインQRコード」で表示できます。",
                    style = PrimaryStyle.regular(color = colorResource(R.color.link_color))
                )
            }

            Text(
                text = "Ver-$version", style = PrimaryStyle.regular(
                    17, colorResource(R.color.primary)
                ), modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    })
}