package com.example.androiddevfest.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.cloud_fragment.*

class CloudFragment : Fragment() {

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


        val adapter = GroupAdapter<GroupieViewHolder>()
        agendaRecycler.adapter=adapter

        adapter.add(CloudStaff())
        adapter.add(CloudStaff())
        adapter.add(CloudStaff())

        viewModel.fetchCloud()
    }


}

class CloudStaff : Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

    override fun getLayout(): Int {
        return R.layout.cloud_item
    }

}