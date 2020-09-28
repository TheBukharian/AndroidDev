package com.example.androiddevfest.Options

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddevfest.Adapters.AgendaAdapter
import com.example.androiddevfest.MainActivity
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import kotlinx.android.synthetic.main.activity_agenda.*
import kotlinx.android.synthetic.main.activity_agenda.actBarAgenda
import kotlinx.android.synthetic.main.activity_agenda.arrowAgenda
import kotlinx.android.synthetic.main.activity_agenda.lightBtnAgenda
import kotlinx.android.synthetic.main.activity_speakers.*

class Speakers : AppCompatActivity() {
    lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speakers)



//==================================================================================================

        themeCheck()
        arrowSpeakers.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        lightBtnSpeakers.setOnClickListener {
            if (!saveData.loadDarkModeState()) {
                saveData.setDarkNodeState(true)
                darkSet()
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)


            }else{
                saveData.setDarkNodeState(false)
                darkOff()
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        }




    }

    fun themeCheck(){
        saveData = SaveData(this)
        if (saveData.loadDarkModeState()){
            darkSet()
        }else{
            darkOff()
        }
    }
    private fun darkSet(){
        setTheme(R.style.DarkTheme)
        lightBtnSpeakers.setImageResource(R.drawable.darklight)
        actBarSpeakers.setBackgroundResource(R.drawable.act_darkoption)
        speakersMain.setBackgroundResource(R.drawable.gradient_dark)



    }
    private fun darkOff(){
        setTheme(R.style.AppTheme)
        lightBtnSpeakers.setImageResource(R.drawable.light)
        actBarSpeakers.setBackgroundResource(R.drawable.actlight)
        speakersMain.setBackgroundResource(R.drawable.actlight)


    }

}