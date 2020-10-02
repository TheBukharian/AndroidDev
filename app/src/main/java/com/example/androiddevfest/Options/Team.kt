package com.example.androiddevfest.Options

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.androiddevfest.Fragments.CloudMates
import com.example.androiddevfest.Fragments.CloudStaff
import com.example.androiddevfest.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.cloud_fragment.*

class Team : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)
    }
    private fun fetchCloudsName(){

        val ref = FirebaseDatabase.getInstance().getReference("/speakers")

        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<GroupieViewHolder>()


                p0.children.forEach {
                    val values = it.getValue(CloudMates::class.java)
                    val key = it.key.toString()
                    if (values!=null){
                        adapter.add(CloudStaff(values))
                        Log.d("CloudValues",it.toString())
                        Log.d("CloudKey",key)


                    }
                }
                agendaRecycler.adapter=adapter
                agendaRecycler.addItemDecoration(
                    DividerItemDecoration(agendaRecycler.context,
                        DividerItemDecoration.HORIZONTAL)
                )

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("CloudTag","ERROR OCCURRED WHILE FETCHING FROM FIREBASE!")

            }
        })
    }
}
class Teammates(val name:String,val photoUrl:String,val title:String){
    constructor():this("","","")

}