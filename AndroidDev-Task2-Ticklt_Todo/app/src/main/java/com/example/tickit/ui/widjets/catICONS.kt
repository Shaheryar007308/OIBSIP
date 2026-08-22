package com.example.tickit.ui.widjets

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun categoryIcon (cat : String) : ImageVector {

    return when(cat){
        "Work" -> Icons.Default.Work
        "Study" -> Icons.Default.School
        "Shopping" -> Icons.Default.ShoppingCart
        "Health" -> Icons.Default.Favorite
        else -> {
            Icons.Default.Person
        }
    }

}