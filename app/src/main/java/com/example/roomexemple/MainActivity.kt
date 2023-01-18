package com.example.roomexemple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var appDB : ContactDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        appDB = Room.databaseBuilder(applicationContext,ContactDataBase::class.java,"contactDB").build()
        appDB = ContactDataBase.getDatabase(this)
        GlobalScope.launch(Dispatchers.IO) {
            appDB.contactDao().insertContact(Contact(0,"Ghi","9874521123", Date()))
        }

    }

    fun getData(view: View) {
        appDB.contactDao().getContact().observe(this, Observer {
            Log.d("Data_From_DB",it.toString())
            Toast.makeText(this, it.toString(),Toast.LENGTH_LONG).show()
        })
    }
}