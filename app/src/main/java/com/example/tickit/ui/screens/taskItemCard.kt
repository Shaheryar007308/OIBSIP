package com.example.tickit.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.elevatedCardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tickit.data.room_database.TaskItem
import com.example.tickit.ui.theme.Dark
import com.example.tickit.ui.theme.grey

@Composable
fun ItemCard(
    item: TaskItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onRadioButtonClick: (Boolean) -> Unit
) {


    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (item.isDone) grey else Color.White
        ),
        border = BorderStroke(1.dp, grey)


    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                onRadioButtonClick(!item.isDone)
            }) {
                Icon(
                    imageVector = if (item.isDone) Icons.Default.Circle else Icons.Default.RadioButtonUnchecked,
                    contentDescription = null,
                    tint = if (item.isDone) Color.Green else grey
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = item.name,
                modifier = Modifier.weight(1f),
                fontWeight = FontWeight.Medium,
                color = if (item.isDone) grey else Dark,
                textDecoration = if (item.isDone) TextDecoration.LineThrough else null
            )


            Row() {
                IconButton(onClick = onEditClick) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = grey,
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(onClick = onDeleteClick) {
                    Icon(
                        imageVector = Icons.Default.DeleteOutline, contentDescription = "Delete",
                        tint = grey,
                        modifier = Modifier.size(24.dp)
                    )
                }

            }

        }
    }


}