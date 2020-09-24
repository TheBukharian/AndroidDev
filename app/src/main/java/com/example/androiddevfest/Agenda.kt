package com.example.androiddevfest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Agenda : AppCompatActivity() {
    var lamp : String="1"
    lateinit var saveData : SaveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        saveData = SaveData(this)
        if (saveData.loadDarkModeState()){
            setTheme(R.style.DarkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
    }
}