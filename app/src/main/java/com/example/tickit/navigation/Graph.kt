package com.example.tickit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tickit.authentication.authManager
import com.example.tickit.ui.screens.Login
import com.example.tickit.ui.screens.SIGNIN
import com.example.tickit.ui.screens.ToDoScreen
import com.example.tickit.viewmodel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(viewModel: TaskViewModel){

    var fireInstance = authManager()
    var navCont =  rememberNavController()
    val cuser = fireInstance.currentUser()
    val log = fireInstance.isLogged()
    NavHost(
        navController = navCont,
        startDestination = if(log && cuser?.isEmailVerified == true){
            Routes.MAIN
        } else{
            Routes.LOGIN
        }
    ){
        composable<Routes.LOGIN> {
            Login(navCont)
        }

        composable<Routes.SIGNING> {
            SIGNIN(navCont)
        }

        composable<Routes.MAIN> {
            ToDoScreen(
                viewmodel = viewModel , navCont
            )
        }
    }
}