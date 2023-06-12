package com.example.itsmungapplication.vo

data class MessageVO (
    var messageId: String? = "",
    var matchingId: String? = "",
    var expertId: String? = "",
    var userId: String? = "",
    var senderType: String? = "",
    var content: String? = "",
    var sendTime: String? = ""
)