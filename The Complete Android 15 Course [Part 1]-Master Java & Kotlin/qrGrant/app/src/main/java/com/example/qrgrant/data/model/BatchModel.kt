package com.example.qrgrant.data.model

data class BatchModel(
    val name: String, val toDate: String?, val status: String?, val count: Int?, val amount: Int?
)

fun BatchModel.statusText(): String {
    return if (status == "paid") "支払完了" else "支払処理中"
}