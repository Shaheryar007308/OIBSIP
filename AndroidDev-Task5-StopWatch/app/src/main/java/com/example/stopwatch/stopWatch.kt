package com.example.stopwatch

import android.annotation.SuppressLint
import android.widget.Space
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stopwatch.ui.theme.resertButtonColor
import com.example.stopwatch.ui.theme.startButtonColor
import com.example.stopwatch.ui.theme.stopButtonColor
import com.example.stopwatch.ui.theme.timerColor
import com.example.stopwatch.ui.theme.timerText
import com.example.viewmodel.stopwatchViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")

@Composable
fun StopWatch(view : stopwatchViewModel) {

    Scaffold() { innerpadding->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerpadding),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "Background",
                contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
            )


            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Timer", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = timerColor)
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = view.timetext,
                    color = timerText,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().padding(14.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Button(
                        onClick = {
                            view.start()
                        }, modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = startButtonColor
                        )
                    ) {
                        Text(
                            text = if (view.time > 0 && !view.isRunning) "Resume" else "Start",
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = { view.stop() }, modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(
                            containerColor = stopButtonColor
                        )
                    ) {
                        Text(text = "Stop" , fontSize = 15.sp)
                    }

                }

                Button(
                    onClick = { view.reset() } , modifier = Modifier.fillMaxWidth().padding(15.dp).border(0.8.dp , timerColor ,
                        RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp), colors = ButtonDefaults.buttonColors(
                        containerColor = resertButtonColor
                    )
                ) {
                    Text(text = "Reset" , fontSize = 15.sp)
                }

            }


        }
    }




}