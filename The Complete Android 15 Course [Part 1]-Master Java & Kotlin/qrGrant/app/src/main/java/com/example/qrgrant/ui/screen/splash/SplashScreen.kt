package com.example.qrgrant.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = viewModel()
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val version = packageInfo.versionName

    LaunchedEffect(Unit) {
        val isLoggedIn = viewModel.checkLogin()
        if (isLoggedIn) {
            navController.offAll(Routes.HOME)
        } else {
            navController.offAll(Routes.LOGIN)
        }
    }

    Scaffold(content = { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(id = R.color.primary)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.logos),
                    contentDescription = "Logo App",
                    modifier = Modifier.size(200.dp)
                )

                Box(
                    modifier = Modifier
                        .size(200.dp, 32.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "QR補助金申請App",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }
            }

            Text(
                text = "Ver-$version",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 17.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = paddingValues.calculateBottomPadding())
            )
        }
    })
}
