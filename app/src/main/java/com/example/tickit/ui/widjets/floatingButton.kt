package com.example.tickit.ui.widjets

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tickit.ui.theme.Dark


@Composable
fun FloatButton(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        shape = RoundedCornerShape(20.dp),
        containerColor = Dark,
        contentColor = Color.White,
        elevation = FloatingActionButtonDefaults.elevation(12.dp)
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")

        Text(text="Add Task" , modifier = Modifier.padding(start = 8.dp))
    }
}