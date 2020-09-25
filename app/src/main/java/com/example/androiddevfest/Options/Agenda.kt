package com.example.androiddevfest.Options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddevfest.Adapters.AgendaAdapter
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import kotlinx.android.synthetic.main.activity_agenda.*

class Agenda : AppCompatActivity() {
    lateinit var saveData: SaveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agenda)

        val fragmentAdapter=AgendaAdapter(supportFragmentManager)
        viewPager.adapter=fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setIcon(R.drawable.baseline_cloud_queue_black_18dp)
        tabLayout.getTabAt(1)!!.setIcon(R.drawable.baseline_stay_primary_portrait_black_36dp)
        tabLayout.getTabAt(2)!!.setIcon(R.drawable.baseline_public_black_36dp)




    }
}

