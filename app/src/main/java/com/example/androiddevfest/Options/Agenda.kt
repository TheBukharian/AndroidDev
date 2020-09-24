package com.example.androiddevfest.Options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData

class Agenda : AppCompatActivity() {
    var lamp: String = "1"
    lateinit var saveData: SaveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)


    }
}

