package com.example.tickit.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tickit.authentication.authManager
import com.example.tickit.data.room_database.TaskItem
import com.example.tickit.navigation.Routes
import com.example.tickit.ui.theme.Dark
import com.example.tickit.ui.theme.grey
import com.example.tickit.ui.widjets.BottomSheet
import com.example.tickit.ui.widjets.FloatButton
import com.example.tickit.viewmodel.TaskViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ToDoScreen(viewmodel: TaskViewModel, navCont: NavHostController) {



    var fireInstance = authManager()
    var currentId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

    val tasks by viewmodel.alltask(currentId).collectAsState(initial = emptyList())

    var tasktoedit by remember { mutableStateOf<TaskItem?>(null) }

    var showdailouge by remember { mutableStateOf(false) }

    var dai by remember { mutableStateOf(false) }



    Scaffold(
        floatingActionButton = {
            FloatButton(onClick = { showdailouge = true })
        },
        bottomBar = {
            Button(
                onClick = {
                    dai = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(bottom = 30.dp)
                    .padding(start = 25.dp, end = 25.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Dark
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Logout")
            }
        }
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding)
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "My Tasks",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 32.dp),
                color = Dark
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "${tasks.filter { !it.isDone }.size} tasks remaining ", color = grey)
            Spacer(modifier = Modifier.height(15.dp))
            if (tasks.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No Tasks",
                        fontWeight = FontWeight.Medium,
                        fontSize = 24.sp,
                        color = Dark
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(
                        items = tasks,
                        key = { it.id }
                    ) { task ->

                        ItemCard(
                            item = task,
                            onEditClick = {
                                showdailouge = true
                                tasktoedit = task
                            },
                            onDeleteClick = { viewmodel.delete(task) },
                            onRadioButtonClick = { check -> viewmodel.update(task.copy(isDone = check)) }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        HorizontalDivider()

                    }
                }
            }

        }

    }

    if (showdailouge) {
        BottomSheet(
            task = tasktoedit,
            onSave = { name, cat ->
                if (tasktoedit == null) {
                    viewmodel.addTask(
                        TaskItem(
                            name = name,
                            category = cat,
                            isDone = false,
                            userId = currentId
                        )
                    )
                } else {
                    tasktoedit?.let {
                        viewmodel.update(
                            it.copy(
                                name = name,
                                category = cat,
                                userId = currentId
                            )
                        )
                    }
                }
                showdailouge = false
                tasktoedit = null
            },
            onCancel = {
                showdailouge = false
                tasktoedit = null
            }
        )
    }


    if (dai) {
        AlertDialog(
            onDismissRequest = { dai = false },
            confirmButton = {
                TextButton(
                    onClick = {

                        fireInstance.signOut()

                        navCont.navigate(Routes.LOGIN) {
                            popUpTo(navCont.graph.id) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(onClick = { dai = false },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color.Transparent
                    )) {
                    Text(text = "Cancel")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            title = {
                Text(text = "LOGOUT")
            },
            text = {
                Text(text = "Are you sure you want to logout")
            },
            containerColor = Dark,
            titleContentColor = Color.White,
            textContentColor = Color.White,
        )
    }

}