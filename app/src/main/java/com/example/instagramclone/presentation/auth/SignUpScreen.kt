@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.instagramclone.presentation.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.instagramclone.R
import com.example.instagramclone.util.Response
import com.example.instagramclone.util.Screen
import com.example.instagramclone.util.Toast

@Composable
fun SignUpScreen(
    navController: NavHostController,
    authenticationViewModel: AuthenticationViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val userName = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val email = remember {
                mutableStateOf(TextFieldValue(""))
            }
            val password = remember {
                mutableStateOf(TextFieldValue(""))
            }
            Image(
                painter = painterResource(id = R.drawable.ic_insta_logo),
                contentDescription = "Instagram Logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
            )
            Text(
                text = "Sign Up",
                modifier = Modifier.padding(10.dp),
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
            )
            OutlinedTextField(
                value = userName.value,
                onValueChange = {
                    userName.value = it
                },
                label = { Text("UserName") },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = { Text("Enter your username: ") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                ),
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                label = { Text("Email") },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = { Text("Enter your email: ") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email,
                ),

                )
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                },
                label = { Text("Password") },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                placeholder = { Text("Enter your password: ") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password,
                ),
            )
            Button(
                onClick = {
                    val emailVal = email.value.text
                    val passwordVal = password.value.text
                    val userNameVal = userName.value.text
                    authenticationViewModel.signUp(emailVal, passwordVal, userNameVal)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Sign Up")
                when (val response = authenticationViewModel.signUpState.value) {
                    is Response.Loading -> {
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    }

                    is Response.Failure -> {
                        Toast(response.message)
                    }

                    is Response.Success -> {
                        if (response.data) {
                            LaunchedEffect(key1 = true){
                                navController.navigate(Screen.HomeScreen.route) {
                                    popUpTo(Screen.LoginScreen.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    }
                }
            }
            Text(
                text = "Already have an account? Sign In.",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.LoginScreen.route) {
                        launchSingleTop = true
                    }
                })
        }
    }
}