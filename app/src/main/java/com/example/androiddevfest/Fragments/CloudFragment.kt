package com.example.androiddevfest.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
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
import kotlinx.android.synthetic.main.cloud_fragment.*
import kotlinx.android.synthetic.main.cloud_item.view.*

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






        fetchCloudsName()



    }

    private fun fetchCloudsName(){

            val ref = FirebaseDatabase.getInstance().getReference("/speakers")

            ref.addListenerForSingleValueEvent(object:ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    val adapter = GroupAdapter<GroupieViewHolder>()


                    p0.children.forEach {
                        val values = it.getValue(CloudMates::class.java)
                        val key = it.key.toString()
                        if (values!=null){
                            adapter.add(CloudStaff(values))
                            Log.d("CloudValues",it.toString())
                            Log.d("CloudKey",key)

                            fetchCloudsTimeAndTitle(key)

                        }
                    }
                    agendaRecycler.adapter=adapter
                    agendaRecycler.addItemDecoration(DividerItemDecoration(agendaRecycler.context,DividerItemDecoration.HORIZONTAL))

                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("CloudTag","ERROR OCCURRED WHILE FETCHING FROM FIREBASE!")

                }
            })
        }

    private fun fetchCloudsTimeAndTitle(key:String) {

        val ref = FirebaseDatabase.getInstance().getReference("/")

    }


}

class CloudStaff(val cloudMate:CloudMates) : Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.cloudName.text=cloudMate.name
        viewHolder.itemView.staffDescrib.text=cloudMate.title
        Picasso.get().load(cloudMate.photoUrl).into(viewHolder.itemView.itemImage)
    }

    override fun getLayout(): Int {
        return R.layout.cloud_item
    }

}
class CloudMates(val name:String,val photoUrl:String,val title:String){
    constructor():this("","","")

}

