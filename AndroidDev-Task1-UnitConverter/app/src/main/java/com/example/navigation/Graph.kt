package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.screens.History
import com.example.screens.UnitConverterScreen
import com.example.viewmodel.ConversionViewModel


@Composable
fun Graph(viewmodel : ConversionViewModel){



    var navhost = rememberNavController()

    NavHost(
        navhost,
        startDestination = Routes.Home
    ){
        composable<Routes.Home> {
            UnitConverterScreen(
                viewModel = viewmodel ,
                navhost
            )
        }

        composable<Routes.History> {
            History(
                view = viewmodel ,
                navhost
            )
        }
    }
}