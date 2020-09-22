package com.example.androiddevfest

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color.TRANSPARENT
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.first_page_item.view.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val your_Layout = findViewById<RelativeLayout>(R.id.main_container)
        val animationDrawable = your_Layout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(4000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

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
        myRecycler.isNestedScrollingEnabled = false


    }
}

class FirstPageItem1: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Agenda"
        viewHolder.itemView.cardDescribtion.text="Get info about our work,\nthat we are going to do soon."
        viewHolder.itemView.textImg.setImageResource(R.drawable.agenda)

    }
    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem2: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Speakers"
        viewHolder.itemView.cardDescribtion.text="Get a list of our speaker teammates."
        viewHolder.itemView.textImg.setImageResource(R.drawable.speakers)


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem3: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Team"
        viewHolder.itemView.cardDescribtion.text="See who works at GDG."
        viewHolder.itemView.textImg.setImageResource(R.drawable.team)


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem4: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Sponsors"
        viewHolder.itemView.cardDescribtion.text="People financing our program."
        viewHolder.itemView.textImg.setImageResource(R.drawable.coin)


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem5: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="FAQ"
        viewHolder.itemView.cardDescribtion.text="Frequently Asking Questions"


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}
class FirstPageItem6: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cardTxt.text="Locate Us"
        viewHolder.itemView.cardDescribtion.text="Find the location where the event is being held."
        viewHolder.itemView.textImg.setImageResource(R.drawable.map)


    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}