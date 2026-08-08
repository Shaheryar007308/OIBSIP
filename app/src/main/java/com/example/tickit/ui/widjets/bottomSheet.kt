package com.example.tickit.ui.widjets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onSave: (String) -> Unit ,
    onCancel : () -> Unit
) {



    var taskname by remember { mutableStateOf(task?.name ?:"") }

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
                text = if(task == null)"Create new task" else "Update Task",
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
                    unfocusedBorderColor = grey
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Task, contentDescription = "tasks")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {onSave(taskname.trim())},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Dark
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = taskname.isNotBlank()
            ) {
                Text(text = if (task == null)"Save Task" else "Update task")
            }
        }
    }

}