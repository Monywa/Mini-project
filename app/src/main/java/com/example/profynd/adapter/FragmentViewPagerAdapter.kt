package com.example.profynd.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.profynd.fragment.LoginFragment
import com.example.profynd.fragment.signup_fragment

class FragmentViewPagerAdapter(ctx: Context,fm:FragmentManager):FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {
       when(position){
           0 ->return LoginFragment()
           else->return signup_fragment()
       }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "LOG IN"
            else->return "SIGN UP"
        }
    }

    override fun getCount(): Int {
        return 2
    }

}