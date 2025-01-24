package com.example.chatroom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatroom.group2610035.Group2610035
import com.example.chatroom.iphone1313pro1.IPhone1313Pro1
import com.example.chatroom.screens.ChatRoomListScreen
import com.example.chatroom.screens.ChatScreen
import com.example.chatroom.screens.HomeScreen
import com.example.chatroom.screens.Screen
import com.example.chatroom.screens.SignInScreen
import com.example.chatroom.screens.SignUpScreen
import com.example.chatroom.ui.theme.ChatroomTheme
import com.example.chatroom.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatroomTheme {
                val navController  = rememberNavController()
                val viewModel: AuthViewModel = viewModel()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Group2610035()
//                   Navigation(navController = navController, viewModel)
                }
            }
        }
    }
}


@Composable
fun Navigation(
    navController: NavHostController,
    authViewModel: AuthViewModel

){
    NavHost(navController = navController, startDestination = "homeScreen"){
        composable(Screen.HomeScreen.route){
            HomeScreen(
                onRectangle24Tapped = {
                    navController.navigate(Screen.SigninScreen.route)
                },
                onRectangle25Tapped = {
                    navController.navigate(Screen.SignupScreen.route)
                }
            )
        }

        composable(Screen.SignupScreen.route){
            SignUpScreen(
                onAlreadyHaveAnAccountLoginTapped = { navController.navigate(Screen.SigninScreen.route) },
                authViewModel = authViewModel
            )

        }

        composable(Screen.SigninScreen.route) {
            SignInScreen(
                onDonTHaveAnAccountRegisterNowTapped = { navController.navigate(Screen.SignupScreen.route) },
                authViewModel = authViewModel,
                onSigninSuccess = { navController.navigate(Screen.ChatRoomsScreen.route) }

            )
        }

        composable(Screen.ChatRoomsScreen.route){
            ChatRoomListScreen(){room->
                navController.navigate("${Screen.ChatScreen.route}/${room.id}")
            }
        }

        composable("${Screen.ChatScreen.route}/{roomId}") {
            val roomId: String = it.arguments?.getString("roomId") ?: ""
            ChatScreen(roomId = roomId)
        }

    }

}

