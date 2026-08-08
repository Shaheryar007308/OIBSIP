package com.example.tickit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tickit.data.room_database.TaskDataBase
import com.example.tickit.data.room_database.TaskItem
import com.example.tickit.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private var dao = TaskDataBase.getdb(application).taskDao()
    private var repo = TaskRepository(dao)

    var alltask: StateFlow<List<TaskItem>> = repo.getall()
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addTask(task: TaskItem){
        viewModelScope.launch {
            repo.insert(task)
        }
    }

    fun update(task: TaskItem){
        viewModelScope.launch {
            repo.update(task)
        }
    }

    fun delete(task: TaskItem){
        viewModelScope.launch {
            repo.delete(task)
        }
    }
}