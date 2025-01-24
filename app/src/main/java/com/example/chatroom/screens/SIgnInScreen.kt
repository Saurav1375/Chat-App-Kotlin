package com.example.chatroom.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.chatroom.R
import com.example.chatroom.data.Result
import com.example.chatroom.signin.outfit
import com.example.chatroom.viewmodel.AuthViewModel

@Composable
fun SignInScreen(
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    onDonTHaveAnAccountRegisterNowTapped: () -> Unit = {},
    onSigninSuccess: () -> Unit = {}
) {
    var password_login by remember { mutableStateOf("") }
    var email_login by remember { mutableStateOf("") }

    val result by authViewModel.authResult.observeAsState()
    val context = LocalContext.current

    Box(modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            LetsSignYouIn(modifier = Modifier.padding(top = 32.dp))
            WelcomeBackYouHaveBeenMissed(modifier = Modifier.padding(top = 8.dp))
            
            Spacer(modifier = Modifier.height(50.dp))
            CustomTextField(
                label = "Email",
                value = email_login,
                onValueChanged = { email_login = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                label = "Password",
                value = password_login,
                onValueChanged = { password_login = it }
            )
            ForgotPassword(modifier = Modifier.padding(top = 10.dp, start = 180.dp))
            Spacer(modifier = Modifier.height(30.dp))
            SigninButton(
                onSigninButtonTapped = {
                            authViewModel.signIn(email_login, password_login)
                           when (result){
                               is Result.Success->{
                                   onSigninSuccess()
                               }

                               is Result.Error->{
                                   Toast.makeText(
                                       context,
                                       "Authentication Failed",
                                       Toast.LENGTH_LONG
                                   ).show()

                               }

                               else->{
                                   Toast.makeText(
                                       context,
                                       "Authentication Failed Check your Internet",
                                       Toast.LENGTH_LONG
                                   ).show()
                               }
                           }
                },
            )

            Column (
                modifier  = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "or ",
                    fontSize = 18.sp,
                    fontFamily = outfit,
                    lineHeight = 1.26.em,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Divider(modifier = Modifier.padding(top = 8.dp, end = 18.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    GoogleLogo(modifier = Modifier.padding(top = 3.dp)) {
                        Vector54()
                        Vector53()
                        Vector52()
                        Vector51()
                    }

                    FacebookIcon()
                    Vector50()

                }

                DonTHaveAnAccountRegisterNow(
                    onDonTHaveAnAccountRegisterNowTapped = onDonTHaveAnAccountRegisterNowTapped,
                )
            }


        }








    }
}

@Preview(showBackground = true)
@Composable
private fun SignInScreenPreview() {
    MaterialTheme {
        SignInScreen(
            authViewModel  = AuthViewModel(),
            onSigninSuccess = {},
            onDonTHaveAnAccountRegisterNowTapped = {},
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Composable
fun LetsSignYouIn(modifier: Modifier = Modifier) {
    Text(
        text = "Lets Sign you in",
        fontSize = 37.sp,
        fontFamily = outfit,
        lineHeight = 1.26.em,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Composable
fun WelcomeBackYouHaveBeenMissed(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome Back ,\nYou have been missed",
        fontSize = 28.sp,
        fontFamily = outfit,
        lineHeight = 1.26.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun SigninButton(
    onSigninButtonTapped: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = Modifier.size(342.dp, 49.dp),
        onClick = { onSigninButtonTapped() },
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)

    ) {
        Text(
            text = "Sign in",
            fontSize = 18.sp,
            fontFamily = outfit,
            color = Color.White,
            fontWeight = FontWeight.Medium,
        )
       
    }

}


@Composable
fun DonTHaveAnAccountRegisterNow(
    onDonTHaveAnAccountRegisterNowTapped: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = buildAnnotatedString {
            append("Don’t have an account ? ")
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontFamily = outfit,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Register Now")
            }
        },
        fontSize = 16.sp,
        fontFamily = outfit,
        color = Color(85, 83, 83),
        lineHeight = 1.23.em,
        textAlign = TextAlign.Left,
        modifier = modifier
            .clickable(onClick = onDonTHaveAnAccountRegisterNowTapped)
            .width(272.dp)
            .height(43.dp)
    )
}


@Composable
fun ForgotPassword(modifier: Modifier = Modifier) {
    Text(
        text = "Forgot Password ?",
        fontSize = 15.sp,
        fontFamily = outfit,
        lineHeight = 1.26.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}



@Composable
fun Vector54(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in_vector54),
        contentDescription = null,
        modifier = modifier
            .padding(start = 11.73.dp, top = 9.37.dp, end = 0.01.dp, bottom = 2.85.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
fun Vector53(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in_vector53),
        contentDescription = null,
        modifier = modifier
            .padding(start = 1.25.dp, top = 13.73.dp, end = 3.50.dp, bottom = 0.08.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
fun Vector52(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in_vector52),
        contentDescription = null,
        modifier = modifier
            .padding(start = 0.001.dp, top = 6.26.dp, end = 17.94.dp, bottom = 6.40.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
fun Vector51(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in_vector51),
        contentDescription = null,
        modifier = modifier
            .padding(start = 1.25.dp, top = 0.dp, end = 3.43.dp, bottom = 13.81.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
fun GoogleLogo(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .width(23.dp)
            .height(23.dp)
    ) {
        content()
    }
}

@Composable
fun FacebookIcon(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in_facebook_icon),
        contentDescription = null,
    )
}

@Composable
fun Vector50(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.sign_in_vector50),
        contentDescription = null,
    )
}
