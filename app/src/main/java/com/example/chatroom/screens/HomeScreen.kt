package com.example.chatroom.screens

import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.chatroom.R
import com.example.chatroom.iphone1313pro1.IPhone1313Pro1
import com.example.chatroom.iphone1313pro1.outfit
import com.example.chatroom.iphone1313pro1.ubuntu

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onRectangle24Tapped: () -> Unit = {},
    onRectangle25Tapped: () -> Unit = {}

){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp)
                .scale(1.8f),
            painter = painterResource(R.drawable.screenshot_2024_09_02_184955),
            contentDescription = null,
        )

        Text(
            modifier = Modifier.padding(top = 130.dp),
            text = "Team work all",
            fontSize = 34.sp,
            fontFamily = outfit,
            fontWeight = FontWeight.W600,
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Id potenti nisl tellus vestibulum dictum luctus cum habitasse augue. Convallis vitae, dictum justo, iaculis id. Cras a ac augue netus egestas semper varius facilisis id.",
            fontSize = 16.sp,
            fontFamily = ubuntu,
            lineHeight = 1.149.em,
            maxLines = Int.MAX_VALUE,
            textAlign = TextAlign.Center

        )
        Spacer(modifier = Modifier.height(60.dp))
        Row {
            Button(
                modifier = Modifier.size(170.dp, 60.dp),
                onClick = { onRectangle24Tapped() },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)

            ) {
                androidx.compose.material3.Text(
                    text = "Sign in",
                    fontSize = 18.sp,
                    fontFamily = com.example.chatroom.signin.outfit,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
            }

            Button(
                modifier = Modifier.size(170.dp, 60.dp),
                onClick = { onRectangle25Tapped() },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(110,119,246))

            ) {
                androidx.compose.material3.Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontFamily = com.example.chatroom.signin.outfit,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }

}