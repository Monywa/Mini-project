package com.example.profynd

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.profynd.R.id.viewpager_id
import com.example.profynd.adapter.FragmentViewPagerAdapter
import com.example.profynd.fragment.signup_fragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val tabLayout = findViewById<TabLayout>(R.id.tablayout_id)
        val viewPager = findViewById<ViewPager>(viewpager_id)


        var adapter=FragmentViewPagerAdapter(this,supportFragmentManager)

        viewPager.adapter=adapter

        tabLayout.setupWithViewPager(viewPager)


        val tab = tabLayout.getTabAt(1)//to select signUp page
        tab!!.select()
    }

    public fun jumpToLogin(view: View){
        val viewPager = findViewById<ViewPager>(viewpager_id)
        if(viewPager.getCurrentItem()>=1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
        }
        else viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
    }//text swiper login to sign up



}
