package com.example.chatroom.data

data class Message(
    val text: String ="",
    val name: String = "",
    val timestamp: Long  =  System.currentTimeMillis() ,// Ensure this is in milliseconds
    val senderId: String = "",
    val isSentByCurrentUser: Boolean = true
)
