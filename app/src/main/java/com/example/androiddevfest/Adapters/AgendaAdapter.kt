package com.example.androiddevfest.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.androiddevfest.Fragments.CloudFragment
import com.example.androiddevfest.Fragments.MobileFragment
import com.example.androiddevfest.Fragments.WebFragment

class AgendaAdapter(fm:FragmentManager):FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                CloudFragment()

            }
            1->{
                MobileFragment()
            }
            else->{
                WebFragment()
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