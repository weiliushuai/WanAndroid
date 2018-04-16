package com.example.administrator.wanandroid.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter



/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
class MainAdapter: FragmentPagerAdapter{


    private var fragments:ArrayList<Fragment> ?= null

    constructor(fm: FragmentManager, fragments:ArrayList<Fragment>):super(fm){
        this.fragments = fragments
    }

    override fun getItem(position: Int): android.support.v4.app.Fragment {
       return fragments!!.get(position)
    }


    override fun getCount(): Int {
       return fragments!!.size
    }

}