package com.example.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record : ConversionRecord)

    @Delete
    suspend fun delete(record: ConversionRecord)

    @Query("SELECT * FROM conversion_history ORDER BY id DESC")
    fun getAllRecord() : Flow<List<ConversionRecord>>

}