package com.example.androiddevfest.Fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
import kotlinx.android.synthetic.main.activity_agenda.*
import kotlinx.android.synthetic.main.cloud_fragment.*
import kotlinx.android.synthetic.main.cloud_item.*

class CloudFragment : Fragment(){

    companion object {
        fun newInstance() = CloudFragment()
    }
    private lateinit var viewModel: CloudViewModel
    lateinit var saveData: SaveData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cloud_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CloudViewModel::class.java)
        saveData = SaveData(this.requireContext())
        if (saveData.loadDarkModeState()){
            cloudMainBack.setBackgroundResource(R.drawable.gradient_dark)
        }else{
            cloudMainBack.setBackgroundResource(R.drawable.actlight)
        }



        val adapter = GroupAdapter<GroupieViewHolder>()
        agendaRecycler.adapter=adapter

        adapter.add(CloudStaff())
        adapter.add(CloudStaff())
        adapter.add(CloudStaff())

        fetchCloudsName()



    }

    fun fetchCloudsName(){
            val ref = FirebaseDatabase.getInstance().getReference("/sessions/101")
            ref.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        Log.d("CloudFragment",it.toString())
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })


    }


}

class CloudStaff : Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.cloud_item
    }

}

