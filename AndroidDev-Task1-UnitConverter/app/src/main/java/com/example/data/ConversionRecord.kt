package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conversion_history")
data class ConversionRecord(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0 ,
    var inputValue : Double,
    var inputUnit : String ,
    var outputValue : Double ,
    var outputUnit : String
)
