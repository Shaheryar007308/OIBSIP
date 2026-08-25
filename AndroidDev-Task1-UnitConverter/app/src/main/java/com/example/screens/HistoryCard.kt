package com.example.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ConversionRecord
import com.example.unitconverter.ui.theme.Border
import com.example.unitconverter.ui.theme.InputBackground
import com.example.unitconverter.ui.theme.MainText
import com.example.unitconverter.ui.theme.PrimaryBlue
import com.example.unitconverter.ui.theme.SecondaryText
import com.example.unitconverter.ui.theme.SuccessGreen

@Preview(showBackground = true)
@Composable
fun HistoryCard(
    record : ConversionRecord,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal =6.dp, vertical = 8.dp)
            .height(64.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = InputBackground
        ),
        border = BorderStroke(1.dp, Border)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize().padding(2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "From",
                fontSize = 14.sp,
                color = SecondaryText,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = record.inputValue.toString(),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = record.inputUnit,
                fontSize = 14.sp,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "→",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryBlue
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "To",
                fontSize = 14.sp,
                color = SecondaryText,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = String.format("%.2f", record.outputValue),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = SuccessGreen
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = record.outputUnit,
                fontSize = 14.sp,
                color = SecondaryText
            )

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(onClick = {
                onDelete()
            }) {
                Icon(
                    Icons.Default.DeleteOutline,
                    contentDescription = "Del Button",
                    tint = Color.Red
                )
            }
        }
    }
}
