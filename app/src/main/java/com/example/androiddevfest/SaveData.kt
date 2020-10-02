package com.example.androiddevfest

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SaveData(context:Context) {
    private var sharedPreferences : SharedPreferences =context.getSharedPreferences("file",MODE_PRIVATE)

    fun setCount(order:Int){
        val editor=sharedPreferences.edit()
        editor.putInt("b",order)
        editor.apply()
    }
    fun  getCount():Int{
        val order =sharedPreferences.getInt("b",1)
        return (order)
    }


    fun setDarkNodeState(state : Boolean){
        val editor=sharedPreferences.edit()
        editor.putBoolean("Dark",state!!)
        editor.apply()
    }

    fun loadDarkModeState() : Boolean{
        val state =sharedPreferences.getBoolean("Dark",false)
        return (state)
    }

}