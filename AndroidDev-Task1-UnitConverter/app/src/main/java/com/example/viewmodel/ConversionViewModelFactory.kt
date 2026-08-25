package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ConversionViewModelFactory(private var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConversionViewModel::class.java)){
            return ConversionViewModel(application) as T
        }
        else{
            throw IllegalArgumentException("Unknown Viewmodel Class")
        }
    }
}