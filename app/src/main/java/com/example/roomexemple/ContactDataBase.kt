package com.example.roomexemple

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Contact::class], version = 1)
@TypeConverters(Convertors::class)
abstract class ContactDataBase : RoomDatabase() {
    abstract fun contactDao() : ContactDAO
    companion object {
        @Volatile
        private var dataInstance : ContactDataBase? = null
        fun getDatabase(context: Context) : ContactDataBase {
            if (dataInstance == null) {
                synchronized(this) {
                    dataInstance = Room.databaseBuilder(context.applicationContext,ContactDataBase::class.java,"contactDB").build()
                }
            }
            return dataInstance!!
        }
    }
}