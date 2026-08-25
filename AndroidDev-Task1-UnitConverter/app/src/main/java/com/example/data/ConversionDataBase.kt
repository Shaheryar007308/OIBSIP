package com.example.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ConversionRecord::class], version = 1)
abstract class ConversionDataBase : RoomDatabase() {
    abstract fun dao(): ConversionDao

    companion object {
        @Volatile
        private var INSTANCE: ConversionDataBase? = null

        fun getDB(context: Context): ConversionDataBase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context.applicationContext,
                    ConversionDataBase::class.java,
                    "conversion_db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }


}