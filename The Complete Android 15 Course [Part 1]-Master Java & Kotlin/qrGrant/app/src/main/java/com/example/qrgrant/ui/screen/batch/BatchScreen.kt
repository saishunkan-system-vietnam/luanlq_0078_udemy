package com.example.qrgrant.ui.screen.batch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.data.model.statusText
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun BatchScreen(navController: NavController) {
    val viewModel: BatchViewModel = viewModel()
    val context = LocalContext.current

    val batchs by viewModel.batches.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadData(context)
    }

    Scaffold(containerColor = Color.White, topBar = {
        CustomAppbar("精算状況確認", navController, backGroundColor = R.color.orange_color)
    }, content = { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = if (viewModel.isLoading.value) Alignment.Center else Alignment.TopCenter
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = colorResource(R.color.primary))
            } else {
                LazyColumn {
                    items(batchs.size) { index ->
                        val batch = batchs[index]
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .padding(top = 15.dp)
                        ) {
                            Text(batch.name, style = PrimaryStyle.medium())
                            Text(
                                "読み取り期間 : ${batch.toDate}まで", style = PrimaryStyle.regular()
                            )
                            Text(
                                "ステータス : ${batch.statusText()}", style = PrimaryStyle.regular()
                            )
                            Text("精算枚数 : ${batch.count}枚", style = PrimaryStyle.regular())

                            Text("精算額 : ${batch.amount}円", style = PrimaryStyle.regular())

                            Spacer(modifier = Modifier.height(15.dp))
                            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                        }
                    }
                }
            }
        }
    })
}