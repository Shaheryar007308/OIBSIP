package com.example.repository

import com.example.data.ConversionDao
import com.example.data.ConversionRecord
import kotlinx.coroutines.flow.Flow

class ConversionRepo(private var dao : ConversionDao) {

    fun getAllRecords(): Flow<List<ConversionRecord>>{
        return dao.getAllRecord()
    }

    suspend fun insert(record: ConversionRecord){
        return dao.insert(record)
    }

    suspend fun delete(record: ConversionRecord){
        return dao.delete(record)
    }
}