package com.example.viewmodel

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class stopwatchViewModel : ViewModel() {

    var isRunning by mutableStateOf(false)
    var time by mutableStateOf(0)


    var timetext by mutableStateOf("00:00:00")

    var handler = Handler(Looper.getMainLooper())
    var runable = object : Runnable {
        @SuppressLint("DefaultLocale")
        override fun run() {
            time++
            var hours =(time / 3600)
            var minutes=(time % 3600) / 60
            var seconds =(time % 60)
            timetext = String.format("%02d:%02d:%02d" , hours , minutes , seconds)
            handler.postDelayed(this , 1000)
        }
    }

    fun start(){
        if(!isRunning){
            isRunning = true
            handler.postDelayed(runable , 1000)
        }
    }

    fun stop(){
        handler.removeCallbacks(runable)
        isRunning = false
    }

    fun reset(){
        stop()
        time=0
        timetext="00:00:00"
    }


}