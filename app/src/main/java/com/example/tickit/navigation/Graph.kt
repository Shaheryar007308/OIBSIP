package com.example.tickit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tickit.ui.screens.Login
import com.example.tickit.ui.screens.SIGNIN
import com.example.tickit.ui.screens.ToDoScreen
import com.example.tickit.viewmodel.TaskViewModel

@Composable
fun NavGraph(viewModel: TaskViewModel){



    var navCont =  rememberNavController()
    NavHost(
        navController = navCont,
        startDestination = Routes.LOGIN
    ){
        composable<Routes.LOGIN> {
            Login(navCont)
        }

        composable<Routes.SIGNING> {
            SIGNIN(navCont)
        }

        composable<Routes.MAIN> {
            ToDoScreen(
                viewmodel = viewModel
            )
        }
    }
}