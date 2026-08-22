package com.example.tickit.ui.screens

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tickit.R
import com.example.tickit.navigation.Routes
import com.example.tickit.ui.theme.Dark
import com.example.tickit.ui.theme.grey
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun Login(navCont: NavHostController) {


    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var shown by remember { mutableStateOf(false) }

    var auth = remember { FirebaseAuth.getInstance() }
    var loginError by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    var emailError = mail.isNotBlank() &&
            !Patterns.EMAIL_ADDRESS.matcher(mail).matches()

    Scaffold(

    )
    { innerPading ->

        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(innerPading),
            horizontalAlignment = Alignment.CenterHorizontally

            // verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = "Icon",
                Modifier.size(190.dp)
            )

            Text(text = "Ticklt ", fontWeight = FontWeight.Bold, fontSize = 35.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Stay organized, one tick at a time ", fontWeight = FontWeight.SemiBold)


            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {


                Text(
                    text = "Welcome Back! ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(14.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    value = mail,
                    onValueChange = { mail = it },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Dark,
                        unfocusedBorderColor = grey,
                        focusedTextColor = grey
                    ),
                    placeholder = { Text(text = "Email Address") },
                    isError = emailError,
                    supportingText = {
                        if (emailError) {
                            Text(text = "Enter a valid email address")
                        }
                    }
                )



                OutlinedTextField(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Dark,
                        unfocusedBorderColor = grey,
                        focusedTextColor = grey
                    ),
                    placeholder = { Text(text = "Password") },
                    visualTransformation = if (shown) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = { shown = !shown }
                        ) {
                            Icon(
                                imageVector = if (shown) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = ""
                            )
                        }

                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )

                Button(
                    onClick = {

                        var clean = mail.trim()

                        loginError = when {
                            !Patterns.EMAIL_ADDRESS.matcher(clean)
                                .matches() -> "Enter valid email address"

                            password.isBlank() -> "Enter your password"
                            else -> ""
                        }

                        if (loginError.isNotBlank()) return@Button

                        isLoading = true

                        auth.signInWithEmailAndPassword(clean, password)
                            .addOnCompleteListener { log ->

                                isLoading = false
                                if (log.isSuccessful) {
                                    var user = auth.currentUser

                                    if (user?.isEmailVerified == false) {
                                        loginError = "Please verify your email before logging"
                                        auth.signOut()
                                        return@addOnCompleteListener
                                    }

                                    navCont.navigate(Routes.MAIN) {
                                        popUpTo(Routes.LOGIN) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }

                                } else {
                                    loginError = "Incorrect email or password"
                                }
                            }


                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Dark
                    ),
                    shape = RoundedCornerShape(12.dp),
                    enabled = mail.isNotBlank() &&
                            password.isNotBlank()
                ) {
                    Text(
                        text = if (isLoading) "Logging in..." else "Login",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }

                if (loginError.isNotBlank()) {
                    Text(
                        text = loginError,
                        color = Color.Red,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Don't have an account?")
                    TextButton(onClick = { navCont.navigate(Routes.SIGNING) }) {
                        Text(text = "Sign Up")
                    }
                }


            }

        }


    }

}