package com.example.androiddevfest.Options

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.example.androiddevfest.BuildConfig
import com.example.androiddevfest.MainActivity
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_sponsors.*
import kotlinx.android.synthetic.main.expand_item.view.*

class Sponsors : AppCompatActivity() {

    lateinit var saveData: SaveData
    val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sponsors)
        saveData = SaveData(this)
        if (saveData.loadDarkModeState()){
            darkSet()
        }else{
            darkOff()
        }

        arrowSponsors.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
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


            } else {
                saveData.setDarkNodeState(false)
                darkOff()
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }

        shareBtnSponsors.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GDG APP by TheBukharian")
                var shareMessage = "\nLet`s try this GDG application:\n\n"
                shareMessage =
                    shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "Share with "))
            } catch (e: Exception) {
                Log.d("MainActivity", "Couldn` t load the web site")
            }
        }






        fetchSponsors1()
        fetchSponsors2()
    }

    private fun fetchSponsors1() {

        val ref = FirebaseDatabase.getInstance().getReference("/partners/0/logos/0")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                Log.d("ggg", "${snapshot.toString()}")

                val data = snapshot.getValue(SponsorsData::class.java)
                if (data != null) {
                    adapter.add(TextTwo())
                    adapter.add(Sponsor(data))
                    adapter.add(Text())

                }
                sponsorsRecycler.adapter = adapter
            }


            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    private fun fetchSponsors2() {

        val ref = FirebaseDatabase.getInstance().getReference("/partners/1/logos").limitToFirst(10)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {


                snapshot.children.forEach {
                    Log.d("ggg", "${it.toString()}")
                    val data = it.getValue(SponsorsData::class.java)
                    if (data != null) {
                        adapter.add(Sponsor(data))
                    }
                }
                sponsorsRecycler.adapter = adapter
            }



            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


    private fun darkSet() {
        setTheme(R.style.DarkTheme)
        lightBtnSponsors.setImageResource(R.drawable.darklight)
        actBarSponsors.setBackgroundResource(R.drawable.act_darkoption)
        sponsorsMain.setBackgroundResource(R.drawable.gradient_dark)


    }

    private fun darkOff() {
        setTheme(R.style.AppTheme)
        lightBtnSponsors.setImageResource(R.drawable.light)
        actBarSponsors.setBackgroundResource(R.drawable.actlight)
        sponsorsMain.setBackgroundResource(R.drawable.actlight)


    }
}

class Text() : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {


    }

    override fun getLayout(): Int {
        return R.layout.text
    }


}
class TextTwo() : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {


    }

    override fun getLayout(): Int {
        return R.layout.text_two
    }


}
    class Sponsor(val sponsor: SponsorsData) : Item<GroupieViewHolder>() {

        override fun bind(viewHolder: GroupieViewHolder, position: Int) {


            viewHolder.itemView.sponsorName.text = sponsor.name

            //given url picture has too large size, that is why glide giving me the bug while downloading

//            Glide.with(viewHolder.root.context).load("https://storage.googleapis.com/hoverboard-experimental.appspot.com/images/logos/gdg-lviv.svg")
//                .into(viewHolder.itemView.sponsorLogo)


            viewHolder.itemView.moreBtn.setOnClickListener {
                val openURL = Intent(Intent.ACTION_VIEW)
                openURL.data = Uri.parse(sponsor.url)
                startActivity(viewHolder.itemView.context, openURL, null)
            }

        }

        override fun getLayout(): Int {
            return R.layout.expand_item
        }


    }

    class SponsorsData(val name: String, val logoUrl: String, val url: String) {
        constructor() : this("", "", "")

    }

