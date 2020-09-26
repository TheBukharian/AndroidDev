package com.example.androiddevfest

import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.androiddevfest.Options.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.first_page_item.view.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout
import android.view.View as View1

class MainActivity : AppCompatActivity() {

    lateinit var saveData : SaveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        saveData = SaveData(this)
        if (saveData.loadDarkModeState()){
            darkSet()
        }else{
            darkOff()
            val your_Layout = findViewById<RelativeLayout>(R.id.main_container)
            val animationDrawable = your_Layout.background as AnimationDrawable
            animationDrawable.setEnterFadeDuration(4000)
            animationDrawable.setExitFadeDuration(4000)
            animationDrawable.start()
        }


        //implement links
                instaBtn.setOnClickListener{
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.instagram.com/gdgtashkent/?hl=ru")
            startActivity(openURL)
        }
                faceBtn.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.facebook.com/gdgtashkent/")
            startActivity(openURL)
        }
                youtubeBtn.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://www.youtube.com/channel/UCEPvlcWSQJ-lsv-7v20mFBQ")
            startActivity(openURL)
        }
                twitterBtn.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://twitter.com/gdgtashkent")
            startActivity(openURL)
        }
                lightBtn.setOnClickListener{
                    if (!saveData.loadDarkModeState()) {
                        saveData.setDarkNodeState(true)
                        darkSet()

                    }else{
                        saveData.setDarkNodeState(false)
                        darkOff()
                        setAnim()
                    }
                }
                shareBtn.setOnClickListener{
                    try{
                        val shareIntent =Intent(ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(EXTRA_SUBJECT,"GDG APP by TheBukharian")
                        var shareMessage = "\nLet`s try this GDG application:\n\n"
                        shareMessage = shareMessage+ "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID+ "\n\n"
                        shareIntent.putExtra(EXTRA_TEXT,shareMessage)
                        startActivity(createChooser(shareIntent,"Share with "))
                    }
                    catch (e:Exception){
                        Log.d("MainActivity","Couldn` t load the web site")
                    }
                }


        val adapter=GroupAdapter<GroupieViewHolder>()
        adapter.add(FirstPageItem6())
        adapter.add(FirstPageItem5())
        adapter.add(FirstPageItem4())
        adapter.add(FirstPageItem3())
        adapter.add(FirstPageItem2())
        adapter.add(FirstPageItem1())
        myRecycler.adapter=adapter

        val linearLayoutManager = ZoomRecyclerLayout(this)
        val snapHelper = LinearSnapHelper()
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        myRecycler.layoutManager = linearLayoutManager
        snapHelper.attachToRecyclerView(myRecycler)
        myRecycler.isNestedScrollingEnabled = true


    }


    private fun darkSet(){
        setTheme(R.style.DarkTheme)
        lightBtn.setImageResource(R.drawable.darklight)
        main_container.setBackgroundResource(R.drawable.gradient_dark)
        actBar.setBackgroundColor(resources.getColor(R.color.CardTextForDark))
        text.setBackgroundColor(resources.getColor(R.color.CardTextForDark))
    }
    private fun darkOff(){

        setTheme(R.style.AppTheme)
        lightBtn.setImageResource(R.drawable.light)
        main_container.setBackgroundResource(R.drawable.background)
        actBar.setBackgroundColor(resources.getColor(R.color.LinearBackground))
        text.setBackgroundColor(resources.getColor(R.color.LinearBackground))
    }
    private fun setAnim(){
        val your_Layout = findViewById<RelativeLayout>(R.id.main_container)
        val animationDrawable = your_Layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(4000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()
    }
}

class FirstPageItem1: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Agenda"
//        viewHolder.itemView.cardImg.setImageResource(R.drawable.gradient_1)
        viewHolder.itemView.goBtn.setOnClickListener {
            val context=viewHolder.itemView.context
            val intent=Intent(context,Agenda::class.java)
            context.startActivity(intent)

        }



    }
    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem2: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Speakers"
//        viewHolder.itemView.cardImg.setImageResource(R.drawable.gradient_5)
        viewHolder.itemView.goBtn.setOnClickListener {
            val context=viewHolder.itemView.context
            val intent=Intent(context, Speakers::class.java)
            context.startActivity(intent)
        }

    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem3: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Team"
//        viewHolder.itemView.cardImg.setImageResource(R.drawable.gradient_2)
        viewHolder.itemView.goBtn.setOnClickListener {
            val context=viewHolder.itemView.context
            val intent=Intent(context,Team::class.java)
            context.startActivity(intent)
        }


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem4: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Sponsors"
//        viewHolder.itemView.cardImg.setImageResource(R.drawable.gradient_4)
        viewHolder.itemView.goBtn.setOnClickListener {
            val context=viewHolder.itemView.context
            val intent=Intent(context, Sponsors::class.java)
            context.startActivity(intent)

        }

    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }


}
class FirstPageItem5: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="FAQ"
//        viewHolder.itemView.cardImg.setImageResource(R.drawable.gradient_3)
        viewHolder.itemView.goBtn.setOnClickListener {
            val context=viewHolder.itemView.context
            val intent=Intent(context,Faq::class.java)
            context.startActivity(intent)
        }


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem6: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Locate Us"
//        viewHolder.itemView.cardImg.setImageResource(R.drawable.gradient_6)
        viewHolder.itemView.goBtn.setOnClickListener {
            val context=viewHolder.itemView.context
            val intent=Intent(context,Location::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }



}