package com.example.qrgrant.ui.screen.search_history.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.qrgrant.R
import com.example.qrgrant.data.model.HistoryModel
import com.example.qrgrant.ui.navigation.AppErrorState
import com.example.qrgrant.utils.DateUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SearchHistoryViewModel : ViewModel() {
    private val today: LocalDate = LocalDate.now()
    private val f: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日")
    val f2: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年   MM月   dd日")
    private val f3: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val todayTime: String = today.format(f)

    private val historyList = MutableStateFlow<List<HistoryModel>>(emptyList())
    private val _todayHistory = MutableStateFlow<List<HistoryModel>>(emptyList())
    private val _listHistory = MutableStateFlow<List<HistoryModel>>(emptyList())

    private val _totalCoupon = MutableStateFlow("")
    val totalCoupon: StateFlow<String> = _totalCoupon

    private val _isSelectDate = MutableStateFlow(true)
    val isSelectDate: StateFlow<Boolean> = _isSelectDate

    private val _sumAmount = MutableStateFlow("")
    val sumAmount: StateFlow<String> = _sumAmount

    val fromDate = MutableStateFlow(today.format(f3))
    val toDate = MutableStateFlow(today.format(f3))
    val codeCoupons = MutableStateFlow("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _isLoadingToday = MutableStateFlow(false)
    val isLoadingToday: StateFlow<Boolean> = _isLoadingToday

    fun loadData(context: Context) {
        val json = context.resources.openRawResource(R.raw.historys).bufferedReader()
            .use { it.readText() }

        val gson = Gson()
        val type = object : TypeToken<List<HistoryModel>>() {}.type
        val list: List<HistoryModel> = gson.fromJson(json, type)

        historyList.value = list

        filterData(true)

        _totalCoupon.value = "${_todayHistory.value.size}"

        _sumAmount.value = _todayHistory.value.sumOf { it.amount ?: 0 }.toString()
    }

    private fun filterData(isToday: Boolean = false, code: String? = null) {
        if (isToday) {
            _todayHistory.value = historyList.value.filter { history ->
                history.receiveDatetime?.startsWith(today.toString()) == true
            }
        } else {
            _listHistory.value = historyList.value.filter { history ->
                if (!_isSelectDate.value || code != null) {
                    val codeCoupon = code ?: codeCoupons.value
                    val match = history.code?.contains(codeCoupon) == true
                    match
                } else {
                    val date = try {
                        LocalDateTime.parse(
                            history.receiveDatetime,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                        ).toLocalDate().format(f3)
                    } catch (e: Exception) {
                        null
                    }
                    date != null && date >= fromDate.value && date <= toDate.value
                }
            }
        }
    }

    suspend fun handleSearch(
        code: String? = null,
        appErrorState: AppErrorState? = null,
        isToday: Boolean = false,
        showLoading: Boolean = true,
        onDismiss: () -> Unit = {},
        onSuccess: (data: String) -> Unit,
    ) {
        if (showLoading) {
            if (isToday) _isLoadingToday.value = true else _isLoading.value = true
        }


        if (showLoading) {
            delay(500)
            _isLoading.value = false
            _isLoadingToday.value = false
        }

        if (!isToday && appErrorState != null) {
            filterData(code = code)
            if (_listHistory.value.isEmpty()) {
                appErrorState.show("データなし", onDismiss = onDismiss)
                return
            }
        }

        val jsonConfig = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }

        val targetList = if (isToday) _todayHistory.value else _listHistory.value
        val json = jsonConfig.encodeToString(targetList)
        val data = withContext(Dispatchers.IO) {
            URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
        }

        onSuccess(data)
    }


    fun handleSelectRadio(value: Boolean = true) {
        _isSelectDate.value = value
    }

    fun clearInput() {
        fromDate.value = today.format(f3)
        toDate.value = today.format(f3)
        codeCoupons.value = ""
        handleSelectRadio()
    }

    fun selectDate(context: Context, isFromDate: Boolean = true) {
        DateUtils.showDateTimePicker(
            context = context, currentValue = if (isFromDate) fromDate.value else toDate.value
        ) { selected ->
            if (isFromDate) fromDate.value = selected else toDate.value = selected
        }
    }


    fun formatDate(dateStr: String): String {
        val date = LocalDate.parse(dateStr, f3)
        return date.format(f2)
    }
}
