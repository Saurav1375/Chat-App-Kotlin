package com.example.chatroom.screens

sealed class Screen(val route : String){
    data object HomeScreen: Screen("homescreen")
    data object SigninScreen : Screen("loginscreen")
    data object SignupScreen:Screen("signupscreen")
    data object ChatRoomsScreen:Screen("chatroomscreen")
    data object ChatScreen:Screen("chatscreen")
}