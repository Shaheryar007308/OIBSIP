package com.example.tickit.ui.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tickit.data.room_database.TaskItem
import com.example.tickit.ui.theme.Dark
import com.example.tickit.ui.theme.grey
import com.example.tickit.ui.widjets.BottomSheet
import com.example.tickit.ui.widjets.FloatButton
import com.example.tickit.viewmodel.TaskViewModel

@Composable
fun ToDoScreen(viewmodel: TaskViewModel) {

    val view by viewmodel.alltask.collectAsState()
    var tasktoedit by remember { mutableStateOf<TaskItem?>(null) }

    var showdailouge by remember { mutableStateOf(false) }



    Scaffold(
        floatingActionButton = {
            FloatButton(onClick = { showdailouge = true })
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

            Text(text = "${view.filter { !it.isDone }.size} tasks remaining ", color = grey)
            Spacer(modifier = Modifier.height(15.dp))
            if (view.isEmpty()) {
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
                        items = view,
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
            onSave = { name , cat ->
                if (tasktoedit == null) {
                    viewmodel.addTask(TaskItem(name = name, category = cat, isDone = false))
                } else {
                    tasktoedit?.let { viewmodel.update(it.copy(name = name , category = cat)) }
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

}