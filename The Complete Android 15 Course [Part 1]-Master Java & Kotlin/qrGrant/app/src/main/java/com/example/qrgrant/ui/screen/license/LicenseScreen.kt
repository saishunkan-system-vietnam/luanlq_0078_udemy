package com.example.qrgrant.ui.screen.license

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.data.model.LicenseModel
import com.example.qrgrant.ui.components.CustomAppbar
import com.example.qrgrant.ui.theme.PrimaryStyle

@Composable
fun LicenseScreen(navController: NavController) {
    val viewModel: LicenseViewModel = viewModel()
    val context = LocalContext.current

    val licenses by viewModel.license.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var visibleCount by remember { mutableStateOf(20) }


    LaunchedEffect(Unit) {
        viewModel.loadData(context)
    }

    Scaffold(
        topBar = { CustomAppbar("ライセンス", navController) }, containerColor = Color.White
    ) { paddingValues ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center), colorResource(
                        R.color.primary
                    )
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val visibleLicenses = licenses.take(visibleCount)
                itemsIndexed(visibleLicenses) { index, item ->
                    LicenseItemView(item)

                    if (index == visibleLicenses.lastIndex && visibleLicenses.size < licenses.size) {
                        visibleCount = (visibleCount + 20).coerceAtMost(licenses.size)
                    }
                }
            }
        }
    }
}

@Composable
fun LicenseItemView(item: LicenseModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = item.name, style = PrimaryStyle.bold(24)
        )
        item.description?.takeIf { it.isNotEmpty() }
            ?.let { Text(it, style = PrimaryStyle.regular(17)) }
    }
}
