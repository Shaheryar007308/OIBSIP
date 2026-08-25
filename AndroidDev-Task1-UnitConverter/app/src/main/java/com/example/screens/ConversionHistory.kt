package com.example.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigation.Routes
import com.example.unitconverter.R
import com.example.unitconverter.ui.theme.SecondaryText
import com.example.viewmodel.ConversionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun History(
    view: ConversionViewModel,
    navhost: NavHostController
) {
    var records = view.getAllRecords.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Conversion History",
                        fontWeight = FontWeight.Bold,
                        color = SecondaryText
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navhost.navigate(Routes.Home)
                        },
                        modifier = Modifier.background(
                            color = Color.Transparent,
                            RoundedCornerShape(12.dp)
                        )
                    ) {
                        Icon(Icons.Default.KeyboardBackspace, contentDescription = "")
                    }
                },
            )
        }
    ) { innerPadding ->


        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // Background image
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {


            if (records.value.isEmpty()) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Records ",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = SecondaryText
                    )
                }
            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(4.dp) // Added some padding
                ) {
                    items(
                        items = records.value, // Correctly access the list from State
                        key = { it.id }
                    ) { record ->
                        // Assuming HistoryCard takes a record or you update it to do so
                        HistoryCard(
                            record,
                            onDelete = { view.delete(record) }
                        )
                    }
                }
            }
        }


    }

}



