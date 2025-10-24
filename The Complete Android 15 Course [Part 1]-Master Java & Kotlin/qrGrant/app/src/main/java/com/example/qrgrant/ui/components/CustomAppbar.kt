package com.example.qrgrant.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.button.CustomTextButton
import com.example.qrgrant.ui.navigation.back
import com.example.qrgrant.ui.theme.PrimaryStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppbar(
    title: String,
    navController: NavController,
    isBack: Boolean = true,
    backGroundColor: Int = R.color.primary,
    hasDrawer: Boolean = false,
    handlerBack: (() -> Unit)? = null,
    handlerDrawer: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TextAutoSize.StepBased()
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title, style = PrimaryStyle.regular(20, Color.White)
            )
        }, colors = topAppBarColors(
        containerColor = colorResource(backGroundColor)
        ), navigationIcon = {
            if (isBack) {
                CustomTextButton(content = {
                    Row {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            modifier = Modifier.padding(end = 5.dp),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                        Text(
                            "戻る", style = PrimaryStyle.regular(color = Color.White)
                        )
                    }
                }, onClick = {
                    handlerBack?.invoke() ?: navController.back()
                }, fullWidth = false
                )
            } else if (hasDrawer) {
                TextButton(onClick = {
                    handlerDrawer?.invoke()
                }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            modifier = Modifier.padding(end = 5.dp),
                            contentDescription = "Sidebar",
                            tint = Color.White
                        )
                    }
                }
            }
        }, actions = actions
    )
}
