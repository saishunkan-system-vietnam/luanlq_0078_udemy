package com.example.qrgrant.ui.screen.search_history.components.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qrgrant.LocalNavController
import com.example.qrgrant.R
import com.example.qrgrant.ui.components.button.CustomRadioButton
import com.example.qrgrant.ui.components.button.PrimaryButton
import com.example.qrgrant.ui.components.input.InputForm
import com.example.qrgrant.ui.navigation.AppErrorState
import com.example.qrgrant.ui.navigation.Routes
import com.example.qrgrant.ui.navigation.next
import com.example.qrgrant.ui.screen.search_history.view_model.SearchHistoryViewModel
import com.example.qrgrant.ui.theme.PrimaryStyle
import kotlinx.coroutines.launch

@Composable
fun BodySearch(
    navController: NavController, viewModel: SearchHistoryViewModel, appErrorState: AppErrorState
) {
    val context = LocalContext.current
    LocalNavController.current
    val isSelectDate by viewModel.isSelectDate.collectAsState()
    val focusManager = LocalFocusManager.current
    val configuration = LocalConfiguration.current
    val screenWith = configuration.screenWidthDp.dp
    val coroutineScope = rememberCoroutineScope()

    val fromDate by viewModel.fromDate.collectAsState()
    val toDate by viewModel.toDate.collectAsState()
    val codeCoupons by viewModel.codeCoupons.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(0.1f), shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            CustomRadioButton(
                "集計日",
                isSelectDate,
                if (isSelectDate) Color.Black else colorResource(R.color.border_color)
            ) {
                focusManager.clearFocus()
                viewModel.handleSelectRadio()
            }

            InputForm(
                text = viewModel.formatDate(fromDate),
                onTextChanged = { viewModel.fromDate.value = it },
                cornerRadius = 10.dp,
                textAlign = TextAlign.End,
                readOnly = isSelectDate,
                backgroundColor = Color.White,
                isDisable = !isSelectDate,
                enabled = false,
                unfocusedBorderColor = colorResource(R.color.border_color)
            ) {
                focusManager.clearFocus()
                viewModel.selectDate(context)
            }

            Text(
                "~",
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                style = PrimaryStyle.bold(
                    20, colorResource(if (isSelectDate) R.color.body_text else R.color.border_color)
                )
            )

            InputForm(
                text = viewModel.formatDate(toDate),
                onTextChanged = { viewModel.toDate.value = it },
                cornerRadius = 10.dp,
                textAlign = TextAlign.End,
                readOnly = isSelectDate,
                backgroundColor = Color.White,
                isDisable = !isSelectDate,
                enabled = false,
                unfocusedBorderColor = colorResource(R.color.border_color)
            ) {
                focusManager.clearFocus()
                viewModel.selectDate(context, false)
            }

            CustomRadioButton(
                "クーポン番号",
                !isSelectDate,
                if (!isSelectDate) Color.Black else colorResource(R.color.border_color)
            ) {
                focusManager.clearFocus()
                viewModel.handleSelectRadio(false)
            }


            InputForm(
                text = codeCoupons,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .width(screenWith * .7f),
                onTextChanged = { viewModel.codeCoupons.value = it },
                cornerRadius = 10.dp,
                readOnly = isSelectDate,
                backgroundColor = Color.White,
                isDisable = isSelectDate,
                enabled = !isSelectDate,
                unfocusedBorderColor = colorResource(R.color.border_color),
                textAlign = TextAlign.End,
                keyboardType = KeyboardType.Password,
                maxLength = 255,
                patterns = "[\\w._-]"
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            PrimaryButton("クリア", width = 100.dp, backGroundColor = Color.Gray) {
                viewModel.clearInput()
            }
            PrimaryButton("検索",
                width = 200.dp,
                isLoading = isLoading,
                backGroundColor = colorResource(R.color.green_color),
                onClick = if (!isSelectDate && codeCoupons.isEmpty()) null else {
                    {
                        coroutineScope.launch {
                            viewModel.handleSearch(appErrorState = appErrorState) { data ->
                                navController.next(Routes.HISTORY_DETAIL + "/$data")
                            }
                        }
                    }
                })
        }

        HorizontalDivider(color = colorResource(R.color.border_color))

        Text(
            "または",
            style = PrimaryStyle.normal(15, lineHeight = 2),
            modifier = Modifier.padding(vertical = 20.dp)
        )

        PrimaryButton(
            "クーポンを読み取って検索",
            width = 300.dp,
            backGroundColor = colorResource(R.color.green_color)
        ) {
            navController.next(Routes.HISTORY_SCAN)
        }
    }
}