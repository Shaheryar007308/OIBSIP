package com.example.tickit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.tickit.navigation.NavGraph
import com.example.tickit.ui.theme.TickItTheme
import com.example.tickit.viewmodel.TaskViewModel
import com.example.tickit.viewmodel.TaskViewmodelFactory

class MainActivity : ComponentActivity() {

    val viewmodel : TaskViewModel by viewModels {
        TaskViewmodelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TickItTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ToDoScreen(
//                        viewmodel = viewmodel
//                    )
                    NavGraph(viewmodel)

                }
            }
        }
    }
}

