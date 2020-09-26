package com.example.androiddevfest.Options

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.example.androiddevfest.Adapters.AgendaAdapter
import com.example.androiddevfest.Fragments.CloudFragment
import com.example.androiddevfest.MainActivity
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import kotlinx.android.synthetic.main.activity_agenda.*
import kotlinx.android.synthetic.main.activity_agenda.actBarAgenda
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.cloud_fragment.*

class Agenda : AppCompatActivity() {

    lateinit var saveData: SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        val fragmentAdapter=AgendaAdapter(supportFragmentManager)
        viewPager.adapter=fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)


//==================================================================================================

        themeCheck()
        arrowAgenda.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        lightBtnAgenda.setOnClickListener {
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
        lightBtnAgenda.setImageResource(R.drawable.darklight)
        tabLayout.setBackgroundResource(R.drawable.gradient_dark)
        actBarAgenda.setBackgroundResource(R.drawable.act_darkoption)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.cloud_black)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.phone_black)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.web_black)

    }
    private fun darkOff(){
        setTheme(R.style.AppTheme)
        lightBtnAgenda.setImageResource(R.drawable.light)
        tabLayout.setBackgroundResource(R.drawable.actlight)
        actBarAgenda.setBackgroundResource(R.drawable.actlight)
        tabLayout.getTabAt(0)!!.setIcon(R.drawable.baseline_cloud_queue_black_18dp)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.baseline_stay_primary_portrait_black_36dp)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.baseline_public_black_36dp)
    }
}


