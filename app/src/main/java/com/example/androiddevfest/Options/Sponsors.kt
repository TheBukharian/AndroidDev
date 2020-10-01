package com.example.androiddevfest.Options

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import com.example.androiddevfest.BuildConfig
import com.example.androiddevfest.MainActivity
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import kotlinx.android.synthetic.main.activity_speakers.*
import kotlinx.android.synthetic.main.activity_sponsors.*

class Sponsors : AppCompatActivity() {
    lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsors)

        arrowSponsors.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        lightBtnSponsors.setOnClickListener {
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
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }

        shareBtnSponsors.setOnClickListener{
            try{
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"GDG APP by TheBukharian")
                var shareMessage = "\nLet`s try this GDG application:\n\n"
                shareMessage = shareMessage+ "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID+ "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share with "))
            }
            catch (e:Exception){
                Log.d("MainActivity","Couldn` t load the web site")
            }
        }






        fetchSponsors()
    }
    private fun fetchSponsors(){

    }
    private fun darkSet(){
        setTheme(R.style.DarkTheme)
        lightBtnSponsors.setImageResource(R.drawable.darklight)
        actBarSponsors.setBackgroundResource(R.drawable.act_darkoption)
        sponsorsMain.setBackgroundResource(R.drawable.gradient_dark)



    }
    private fun darkOff(){
        setTheme(R.style.AppTheme)
        lightBtnSponsors.setImageResource(R.drawable.light)
        actBarSponsors.setBackgroundResource(R.drawable.actlight)
        sponsorsMain.setBackgroundResource(R.drawable.actlight)


    }
}