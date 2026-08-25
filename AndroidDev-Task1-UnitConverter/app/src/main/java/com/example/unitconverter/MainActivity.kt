package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.navigation.Graph
import com.example.screens.UnitConverterScreen
import com.example.unitconverter.ui.theme.UnitConverterTheme
import com.example.viewmodel.ConversionViewModel
import com.example.viewmodel.ConversionViewModelFactory

class MainActivity : ComponentActivity() {

    val viewmodel: ConversionViewModel by viewModels {
        ConversionViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Graph(viewmodel)
                }
            }
        }
    }
}

