package com.example.tickit.ui.widjets

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Task
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickit.data.room_database.TaskItem
import com.example.tickit.ui.theme.Dark
import com.example.tickit.ui.theme.grey

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun BottomSheet(
    task: TaskItem?,
    onSave: (String, String) -> Unit,
    onCancel: () -> Unit
) {
    var categories = listOf(
        CATEGORIES("Personal", Icons.Default.Person2),
        CATEGORIES("Work", Icons.Default.Work),
        CATEGORIES("Study", Icons.Default.School),
        CATEGORIES("Shopping", Icons.Default.ShoppingBag),
        CATEGORIES("Health", Icons.Default.Healing)
    )
    var selectCategory by remember { mutableStateOf(categories.first()) }
    var dropExpand by remember { mutableStateOf(false) }
    var taskname by remember { mutableStateOf(task?.name ?: "") }

    ModalBottomSheet(
        onDismissRequest = onCancel,
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = if (task == null) "Create new task" else "Update Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Dark
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = taskname,
                onValueChange = { taskname = it },
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(text = "What needs to be done") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Dark,
                    unfocusedBorderColor = grey,
                    focusedTextColor = grey
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Task, contentDescription = "tasks")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))






            ExposedDropdownMenuBox(
                expanded = dropExpand,
                modifier = Modifier.fillMaxWidth(),
                onExpandedChange = { dropExpand = !dropExpand },
            ) {
                OutlinedTextField(
                    value = selectCategory.catname,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),
                    label = {
                        Text("Category")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = selectCategory.caticon,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = dropExpand
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Dark,
                        unfocusedBorderColor = grey
                    )
                )

                ExposedDropdownMenu(
                    expanded = dropExpand,
                    onDismissRequest = {
                        dropExpand = false
                    },
                    modifier = Modifier.background(color = Color.White)
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text = { Text(text = cat.catname) },
                            onClick = {
                                selectCategory = cat
                                dropExpand = false
                            },
                            modifier = Modifier
                                .padding(12.dp)
                                .border(2.dp, color = grey, shape = RoundedCornerShape(12.dp)),
                            leadingIcon = { Icon(cat.caticon, contentDescription = "") },
                            colors = MenuItemColors(
                                textColor =Dark,
                                leadingIconColor = Dark,
                                trailingIconColor = Dark,
                                disabledTextColor = Dark,
                                disabledLeadingIconColor = Dark,
                                disabledTrailingIconColor = Dark
                            )
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    onSave(
                        taskname.trim(),
                        selectCategory.catname
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Dark
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = taskname.isNotBlank()
            ) {
                Text(text = if (task == null) "Save Task" else "Update task")
            }
        }
    }

}


data class CATEGORIES(
    var catname: String,
    var caticon: ImageVector
)

