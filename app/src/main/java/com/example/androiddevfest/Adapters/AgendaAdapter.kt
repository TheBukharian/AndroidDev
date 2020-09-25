package com.example.androiddevfest.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androiddevfest.Fragments.AgendaCloudFragment
import com.example.androiddevfest.Fragments.AgendaMobileFragment
import com.example.androiddevfest.Fragments.AgendaWebFragment

class AgendaAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                AgendaCloudFragment()

            }
            1->{
                AgendaMobileFragment()
            }
            else->{
                AgendaWebFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->{
                "Cloud"
            }
            1->{
                "Mobile"
            }
            else->{
                return "Web"
            }
        }
    }
}