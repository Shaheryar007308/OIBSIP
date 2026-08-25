package com.example.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.navigation.Routes
import com.example.unitconverter.R
import com.example.unitconverter.ui.theme.Border
import com.example.unitconverter.ui.theme.InputBackground
import com.example.unitconverter.ui.theme.MainText
import com.example.unitconverter.ui.theme.PrimaryBlue
import com.example.unitconverter.ui.theme.SecondaryText
import com.example.unitconverter.ui.theme.SuccessGreen
import com.example.viewmodel.ConversionViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UnitConverterScreen(viewModel: ConversionViewModel, navhost: NavHostController) {

    val currentSubUnits =
        viewModel.subunits[viewModel.selectedCategory] ?: emptyList()

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

        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Unit Converter",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Convert anything, instantly",
                fontSize = 14.sp,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.height(28.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = viewModel.inputValue,
                    onValueChange = {
                        viewModel.inputValue = it
                    },
                    label = {
                        Text("Value")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = InputBackground,
                        unfocusedContainerColor = InputBackground,

                        focusedBorderColor = PrimaryBlue,
                        unfocusedBorderColor = Border,

                        focusedLabelColor = PrimaryBlue,
                        unfocusedLabelColor = SecondaryText,

                        focusedTextColor = MainText,
                        unfocusedTextColor = MainText,

                        cursorColor = PrimaryBlue
                    )
                )


                DropDownSelector(
                    label = "Category",
                    items = viewModel.categories,
                    selectedItem = viewModel.selectedCategory,
                    onItemSelected = {
                        viewModel.onCatSelect(it)
                    },
                    modifier = Modifier.weight(1f)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))


            DropDownSelector(
                label = "From Unit",
                items = currentSubUnits,
                selectedItem = viewModel.selectedSourceUnit,
                onItemSelected = {
                    viewModel.selectedSourceUnit = it
                },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(14.dp))


            DropDownSelector(
                label = "To Unit",
                items = currentSubUnits,
                selectedItem = viewModel.selectedTargetUnit,
                onItemSelected = {
                    viewModel.selectedTargetUnit = it
                },
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {
                    viewModel.convertAndSave()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "Convert",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }


            Spacer(modifier = Modifier.height(20.dp))


            if (viewModel.convertedResult.isNotBlank()) {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    shape = RoundedCornerShape(16.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFECFDF5)
                    ),

                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xFFBBF7D0)
                    )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Result",
                            fontSize = 14.sp,
                            color = SecondaryText
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = viewModel.convertedResult,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = SuccessGreen
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = {
                navhost.navigate(Routes.History) {
                    popUpTo(Routes.Home) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
                .padding(bottom = 25.dp),
            containerColor = PrimaryBlue,
            contentColor = Color.White
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = "History"
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownSelector(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            isExpanded = !isExpanded
        },
        modifier = modifier
    ) {

        OutlinedTextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,

            label = {
                Text(text = label)
            },

            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpanded
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),

            singleLine = true,

            shape = RoundedCornerShape(14.dp),

            colors = OutlinedTextFieldDefaults.colors(

                // Background
                focusedContainerColor = InputBackground,
                unfocusedContainerColor = InputBackground,

                // Border
                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = Border,

                // Label
                focusedLabelColor = PrimaryBlue,
                unfocusedLabelColor = SecondaryText,

                // Text
                focusedTextColor = MainText,
                unfocusedTextColor = MainText,

                // Dropdown arrow
                focusedTrailingIconColor = PrimaryBlue,
                unfocusedTrailingIconColor = SecondaryText
            )
        )


        ExposedDropdownMenu(
            expanded = isExpanded,

            onDismissRequest = {
                isExpanded = false
            },
            Modifier.background(Border)


        ) {

            items.forEach { item ->

                DropdownMenuItem(

                    text = {
                        Text(
                            text = item,
                            color = MainText
                        )
                    },

                    onClick = {

                        onItemSelected(item)

                        isExpanded = false
                    },

                    modifier = Modifier
                        .padding(16.dp)
                        .border(
                            2.dp, SecondaryText,
                            RoundedCornerShape(12.dp)
                        )

                )

            }
        }
    }
}