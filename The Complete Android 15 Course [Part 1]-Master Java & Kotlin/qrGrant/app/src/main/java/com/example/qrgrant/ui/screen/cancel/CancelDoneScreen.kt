package com.example.qrgrant.ui.screen.cancel

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.SvgFromAssets
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.components.text.RichText
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun CancelDoneScreen(navController: NavController) {
    Scaffold(
        containerColor = Color.White,
        topBar = { CustomAppbar("完了", navController, false, R.color.red_color) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(100.dp))

                SvgFromAssets("icon_ok.svg", modifier = Modifier.size(116.dp))

                Spacer(modifier = Modifier.height(20.dp))

                RichText(
                    listOf(
                        "補助金申請の" to SpanStyle(
                            colorResource(R.color.body_text), 20.sp, fontWeight = FontWeight.W400
                        ),
                        "取り消し" to SpanStyle(
                            colorResource(R.color.red_color), 20.sp, fontWeight = FontWeight.W400
                        ),
                        "が\n完了しました" to SpanStyle(
                            colorResource(R.color.body_text), 20.sp, fontWeight = FontWeight.W400
                        ),
                    ),
                )
            }

            PrimaryButton(
                "メインメニューへ",
                width = 200.dp,
                style = PrimaryStyle.bold(16)
            ) {
                navController.offAll(Routes.HOME)
            }
        }
    }
}
