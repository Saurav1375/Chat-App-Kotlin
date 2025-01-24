package com.example.chatroom.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.chatroom.R
import com.example.chatroom.signup.outfit
import com.example.chatroom.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    onAlreadyHaveAnAccountLoginTapped: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm_password by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .verticalScroll(scrollState)
        ) {
            LetsRegisterAccount(modifier = Modifier.padding(top = 28.dp))
            HelloUserYouHaveAGreatfulJourney(modifier = Modifier.padding(top = 8.dp))
            Spacer(modifier = Modifier.height(22.dp))
            CustomTextField(label = "Name", value = name, onValueChanged = { name = it })
            Spacer(modifier = Modifier.height(14.dp))
            CustomTextField(label = "Email", value = email, onValueChanged = { email = it })
            Spacer(modifier = Modifier.height(14.dp))
            CustomTextField(label = "Password", value = password, onValueChanged = { password = it })
            Spacer(modifier = Modifier.height(14.dp))
            CustomTextField(label = "Confirm Password", value = confirm_password, onValueChanged = { confirm_password = it })
            Spacer(modifier = Modifier.height(32.dp))

            SigninButton(onSigninButtonTapped = {
                authViewModel.signUp(email = email, password = password, name = name)
                email = ""
                password = ""
                name = ""
                confirm_password = ""
            })
            Spacer(modifier = Modifier.height(20.dp))
            AlreadyHaveAnAccountLogin(
                modifier = Modifier.padding(start = 55.dp),
                onAlreadyHaveAnAccountLoginTapped = onAlreadyHaveAnAccountLoginTapped
            )

        }



    }
}

@Composable
fun LetsRegisterAccount(modifier: Modifier = Modifier) {
    Text(
        text = "Let's Register\nAccount",
        fontSize = 35.sp,
        fontFamily = outfit,  // Replace with your font family
        lineHeight = 1.26.em,  // Adjust as needed
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}


@Composable
fun AlreadyHaveAnAccountLogin(
    onAlreadyHaveAnAccountLoginTapped: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append("Already have an account? ")
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontFamily = outfit,  // Replace with your font family
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Login")
            }
            withStyle(
                style = SpanStyle(
                    color = Color.Black
                )
            ) {
                append(" ")
            }
        },
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,  // Replace with your font family
        color = Color(85, 83, 83),
        lineHeight = 1.26.em,  // Adjust as needed
        textAlign = TextAlign.Left,
        modifier = modifier
            .clickable { onAlreadyHaveAnAccountLoginTapped() }
            .requiredWidth(236.dp)
            .requiredHeight(22.dp)
    )
}

@Composable
fun HelloUserYouHaveAGreatfulJourney(modifier: Modifier = Modifier) {
    Text(
        text = "Hello user, you have\na greatful journey",
        fontSize = 28.sp,
        fontFamily = outfit,  // Replace with your font family
        lineHeight = 1.26.em,  // Adjust if needed
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}


@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    x: Dp = 0.dp,
    y: Dp = 0.dp

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        shape = RoundedCornerShape(5.dp),
        label = {
            Text(
                text = label,
                color = Color.Black
            )
        },
        modifier = Modifier
            .width(342.dp)
            .height(64.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black)
        ),
        singleLine = true,
        textStyle = TextStyle(fontSize = 20.sp, fontFamily = outfit)  // Replace with your font family
    )
}
