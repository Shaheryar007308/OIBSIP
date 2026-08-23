package com.example.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.stopwatch.ui.theme.StopWatchTheme
import com.example.viewmodel.stopwatchViewModel

class MainActivity : ComponentActivity() {

    private val viewmodel : stopwatchViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StopWatchTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StopWatch(viewmodel)
                }
            }
        }
    }
}

