package com.example.androiddevfest.Fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androiddevfest.R
import com.example.androiddevfest.SaveData
import kotlinx.android.synthetic.main.cloud_fragment.*
import kotlinx.android.synthetic.main.mobile_fragment.*

class MobileFragment : Fragment() {
    lateinit var saveData: SaveData

    companion object {
        fun newInstance() = MobileFragment()
    }

    private lateinit var viewModel: MobileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mobile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MobileViewModel::class.java)
        saveData = SaveData(this.requireContext())
        if (saveData.loadDarkModeState()){
            mobileback.setBackgroundResource(R.drawable.gradient_dark)
        }else{
            mobileback.setBackgroundResource(R.drawable.actlight)
        }
    }

}