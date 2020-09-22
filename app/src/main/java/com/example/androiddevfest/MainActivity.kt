package com.example.androiddevfest

import android.content.Intent
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
        adapter.add(FirstPageItems())
        adapter.add(FirstPageItems())
        adapter.add(FirstPageItems())
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

class FirstPageItems: Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.first_page_item
    }

}