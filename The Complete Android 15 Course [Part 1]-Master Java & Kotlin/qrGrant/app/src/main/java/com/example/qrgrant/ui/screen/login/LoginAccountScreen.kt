package com.example.qrgrant.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.components.button.SecondaryButton
import com.example.qrgrant.ui.components.input.InputForm
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.offAll
import com.example.qrgrant.ui.navigation.offUntil
import com.example.qrgrant.ui.screen.login.view_model.LoginAccountViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle
import kotlinx.coroutines.launch

@Composable
fun LoginAccountScreen(navController: NavController) {
    val viewModel: LoginAccountViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val email by viewModel.email.collectAsState()
    val pass by viewModel.pass.collectAsState()
    val errors by viewModel.errors.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    Scaffold(containerColor = Color.White, topBar = {
        CustomAppbar("ログイン", navController)
    }, content = { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = 40.dp), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    "補助金申請\nポータルアカウントログイン",
                    style = PrimaryStyle.regular(16, color = colorResource(R.color.primary)),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                InputForm(
                    text = email,
                    onTextChanged = { viewModel.email.value = it },
                    hint = "メールアドレス",
                    startIcon = Icons.Filled.Email,
                    textError = errors[0],
                )

                Spacer(modifier = Modifier.height(10.dp))

                InputForm(
                    text = pass,
                    onTextChanged = { viewModel.pass.value = it },
                    hint = "パスワード",
                    isPassword = true,
                    startIcon = Icons.Filled.Lock,
                    textError = errors[1],
                )

                Spacer(modifier = Modifier.height(20.dp))

                PrimaryButton(
                    "ログイン", modifier = Modifier.fillMaxWidth(), isLoading = isLoading
                ) {
                    scope.launch {
                        viewModel.handleLogin {
                            navController.offAll(Routes.HOME)
                        }
                    }
                }
            }


            SecondaryButton(
                "QRコードログインを\nご利用の場合はこちら",
                height = 80.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                navController.offUntil(Routes.LOGIN_QR)
            }
        }
    })
}