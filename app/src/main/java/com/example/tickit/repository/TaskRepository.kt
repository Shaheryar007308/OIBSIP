package com.example.tickit.repository

import com.example.tickit.data.room_database.TaskDao
import com.example.tickit.data.room_database.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskRepository(private var dao: TaskDao) {

    fun getall(): Flow<List<TaskItem>> {
        return dao.getall()
    }

    suspend fun insert(task: TaskItem) {
        dao.insert(task)
    }

    suspend fun update(task: TaskItem) {
        dao.update(task)
    }

    suspend fun delete(task: TaskItem) {
        dao.delete(task)
    }
}