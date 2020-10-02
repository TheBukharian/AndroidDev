package com.example.androiddevfest.Options

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androiddevfest.BuildConfig
import com.example.androiddevfest.MainActivity
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_speakers.*
import kotlinx.android.synthetic.main.speaker_item.view.*

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
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }

        fetchSpeakers()
        shareBtnSpeakers.setOnClickListener{
            try{
                val shareIntent =Intent(Intent.ACTION_SEND)
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
    private fun fetchSpeakers(){
        val ref = FirebaseDatabase.getInstance().getReference("/speakers")

        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()

                p0.children.forEach {
                    val values = it.getValue(SpeakersData::class.java)

                    if (values!=null){

                        adapter.add(Speaker(values))
                        Log.d("SpeakersData",it.toString())
                    }
                }
                speakersRecycler.adapter=adapter
                speakersRecycler.addItemDecoration(
                    DividerItemDecoration(speakersRecycler.context,
                        DividerItemDecoration.HORIZONTAL)
                )
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("CloudTag","ERROR OCCURRED WHILE FETCHING FROM FIREBASE!")

            }
        })
    }

}
class Speaker(val speaker:SpeakersData): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.companyName.text=speaker.company
        viewHolder.itemView.speakerName.text=speaker.name
        viewHolder.itemView.companyLogo.setImageResource(R.drawable.gdg)
        viewHolder.itemView.speakerDescrib.text=speaker.title
        viewHolder.itemView.speakerTitle.text=speaker.shortBio
        Picasso.get().load(speaker.photoUrl).into(viewHolder.itemView.speakerImage)

    }

    override fun getLayout(): Int {
        return R.layout.speaker_item
    }

}
class SpeakersData(val name:String,val photoUrl:String,val title:String,val shortBio:String,val company:String){
    constructor():this("","","","","")

}
