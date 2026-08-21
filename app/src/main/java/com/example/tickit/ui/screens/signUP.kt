package com.example.tickit.ui.screens

import android.annotation.SuppressLint
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tickit.authentication.authManager
import com.example.tickit.navigation.Routes
import com.example.tickit.ui.theme.Dark
import com.example.tickit.ui.theme.grey
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun SIGNIN(navCont: NavHostController) {

    var name by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cpassword by remember { mutableStateOf("") }


    var shown by remember { mutableStateOf(false) }
    var cshown by remember { mutableStateOf(false) }

    var auth = remember { FirebaseAuth.getInstance() }

    var context = LocalContext.current

    var signUpError by remember { mutableStateOf("") }

    var isLoading by remember { mutableStateOf(false) }

    val emailError = mail.isNotBlank() &&
            !Patterns.EMAIL_ADDRESS.matcher(mail.trim()).matches()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = {
                        navCont.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = ""
                        )
                    }

                }
            )
        }
    )
    { innerPading ->

        Column(
            modifier = Modifier
                .padding(8.dp)
                .padding(innerPading),


            // verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = "Create Account ", fontWeight = FontWeight.Bold, fontSize = 35.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Organize your day, one tick at a time ", fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(10.dp))

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

                OutlinedTextField(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Dark,
                        unfocusedBorderColor = grey,
                        focusedTextColor = grey
                    ),
                    placeholder = { Text(text = "Full Name") }
                )



                OutlinedTextField(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    value = mail,
                    onValueChange = {
                        mail = it
                        signUpError = " "
                    },
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
                            Text(text = "Enter a valid email address, for example: name@gmail.com")
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



                OutlinedTextField(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxWidth(),
                    value = cpassword,
                    onValueChange = { cpassword = it },
                    shape = RoundedCornerShape(10.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Dark,
                        unfocusedBorderColor = grey,
                        focusedTextColor = grey
                    ),
                    placeholder = { Text(text = "Confirm Password") },

                    visualTransformation = if (cshown) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(
                            onClick = { cshown = !cshown }
                        ) {
                            Icon(
                                imageVector = if (cshown) Icons.Default.Visibility else Icons.Default.VisibilityOff,
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

                        signUpError = when {
                            name.isBlank() -> "Please Enter your name"
                            !Patterns.EMAIL_ADDRESS.matcher(clean)
                                .matches() -> "Enter a valid email address"

                            password.length < 6 -> "Make sure passward must greater than six characters"
                            password != cpassword -> "Password anc confrim password not match with each other "
                            else -> ""
                        }

                        if (signUpError.isNotBlank()) return@Button

                        isLoading = true

                        auth.createUserWithEmailAndPassword(clean, password)
                            .addOnCompleteListener { sign ->

                                isLoading = false

                                if (sign.isSuccessful) {
                                    var user = auth.currentUser

                                    val profile =
                                        UserProfileChangeRequest.Builder().setDisplayName(name)
                                            .build()

                                    user?.updateProfile(profile)

                                    user?.sendEmailVerification()

                                    Toast.makeText(
                                        context,
                                        "Account created. Please verify your email, then log in.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    auth.signOut()

                                    navCont.navigate(Routes.LOGIN) {
                                        popUpTo(Routes.SIGNING) {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                } else {
                                    signUpError = sign.exception?.message
                                        ?: "Could not create account. Try again."
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
                    enabled = !isLoading

                ) {
                    Text(
                        text = if (isLoading) "Creating Account ...." else "Create Account",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }

                if (signUpError.isNotBlank()) {
                    Text(
                        text = signUpError,
                        color = Color.Red,
                        modifier = Modifier.padding(horizontal = 14.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Already have an account?")
                    TextButton(onClick = { navCont.popBackStack() }) {
                        Text(text = "Login")
                    }
                }


            }

        }


    }

}