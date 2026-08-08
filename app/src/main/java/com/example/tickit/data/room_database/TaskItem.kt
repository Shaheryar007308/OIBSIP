package com.example.tickit.data.room_database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tasks")
data class TaskItem(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var name : String ,
    var isDone : Boolean =false
)
