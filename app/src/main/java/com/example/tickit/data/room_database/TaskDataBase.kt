package com.example.tickit.data.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [TaskItem::class], version = 2)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao



    companion object {
        @Volatile
        private var Instance: TaskDataBase? = null

        private val migration1_2 =  object : Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE Tasks ADD COLUMN category TEXT NOT NULL DEFAULT 'Personal'"
                )
            }
        }

        fun getdb(context: Context): TaskDataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java ,
                    "tasks_database"
                ).addMigrations(migration1_2).build().also { Instance = it }
            }
        }
    }
}