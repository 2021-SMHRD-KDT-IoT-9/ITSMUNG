package com.example.itsmungapplication.vo

data class AlarmVO (
    val imageResId: Int = 0,
    var alarmId: String? = "",
    var userId: String? = "",
    var content: String? = "",
    var alarmDate: String? = ""
)