package com.example.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ConversionDataBase
import com.example.data.ConversionRecord
import com.example.repository.ConversionRepo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConversionViewModel(application: Application): AndroidViewModel(application) {

    var dao= ConversionDataBase.getDB(application).dao()

    var repo = ConversionRepo(dao)

    var getAllRecords : StateFlow<List<ConversionRecord>> = repo.getAllRecords().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun insert(record: ConversionRecord){
        viewModelScope.launch {
            repo.insert(record)
        }
    }

    fun delete(record: ConversionRecord){
        viewModelScope.launch {
            repo.delete(record)
        }
    }


    val categories = listOf("Length" , "Weight" , "Temperature")

    val subunits = mapOf(
        "Length" to listOf(
            "m",       // Meters
            "km",      // Kilometers
            "cm",      // Centimeters
            "mm",      // Millimeters
            "Miles"       // Miles
        ),

        "Weight" to listOf(
            "kg",      // Kilograms
            "g",       // Grams
            "lb",      // Pounds
            "Ounces"       // Ounces
        ),

        "Temperature" to listOf(
            "°C",      // Celsius
            "°F",      // Fahrenheit
            "K"        // Kelvin
        )
    )

    var inputValue by mutableStateOf("")
    var selectedCategory by mutableStateOf(categories.first())
    var selectedSourceUnit by mutableStateOf("m")
    var selectedTargetUnit by mutableStateOf("km")
    var convertedResult by mutableStateOf("")

    fun onCatSelect(category : String){
        selectedCategory = category
        val units = subunits[category]
        selectedSourceUnit = units?.firstOrNull() ?: ""
        selectedTargetUnit = units?.getOrNull(1) ?: units?.firstOrNull() ?: ""
        convertedResult=""
    }

    @SuppressLint("DefaultLocale")


    fun convertAndSave() {
        val input = inputValue.toDoubleOrNull()
        if (input == null) {
            convertedResult = "Please enter a valid number"
            return
        }

        val result = calculateResult(input)
        convertedResult = String.format("%.2f %s", result, selectedTargetUnit)

        insert(
            ConversionRecord(
                inputValue = input,
                inputUnit = selectedSourceUnit,
                outputValue = result,
                outputUnit = selectedTargetUnit
            )
        )
    }

    private fun calculateResult(input: Double): Double {
        return when (selectedCategory) {
            "Length" -> convertLength(input, selectedSourceUnit, selectedTargetUnit)
            "Weight" -> convertWeight(input, selectedSourceUnit, selectedTargetUnit)
            "Temperature" -> convertTemperature(input, selectedSourceUnit, selectedTargetUnit)
            else -> 0.0
        }
    }

    fun convertLength(value: Double, from: String, to: String): Double {
        val fromTrimmed = from.trim()
        val toTrimmed = to.trim()

        val valueInMeters = when (fromTrimmed) {
            "km" -> value * 1000.0
            "cm" -> value / 100.0
            "mm" -> value / 1000.0
            "Miles" -> value * 1609.34
            else -> value // m
        }

        // Convert from meters to target unit
        return when (toTrimmed) {
            "km" -> valueInMeters / 1000.0
            "cm" -> valueInMeters * 100.0
            "mm" -> valueInMeters * 1000.0
            "Miles" -> valueInMeters / 1609.34
            else -> valueInMeters // m
        }
    }

    fun convertWeight(value: Double, from: String, to: String): Double {
        val fromTrimmed = from.trim()
        val toTrimmed = to.trim()

        val valueInKg = when (fromTrimmed) {
            "g" -> value / 1000.0
            "lb" -> value / 2.20462
            "Ounces" -> value / 35.274
            else -> value // kg
        }

        return when (toTrimmed) {
            "g" -> valueInKg * 1000.0
            "lb" -> valueInKg * 2.20462
            "Ounces" -> valueInKg * 35.274
            else -> valueInKg // kg
        }
    }

    fun convertTemperature(value: Double, from: String, to: String): Double {
        val fromTrimmed = from.trim()
        val toTrimmed = to.trim()

        if (toTrimmed == fromTrimmed) return value

        val valueInCelsius = when (fromTrimmed) {
            "°F" -> (value - 32) * 5 / 9
            "K" -> value - 273.15
            else -> value // °C
        }

        return when (toTrimmed) {
            "°F" -> (valueInCelsius * 9 / 5) + 32
            "K" -> valueInCelsius + 273.15
            else -> value // °C
        }
    }




}