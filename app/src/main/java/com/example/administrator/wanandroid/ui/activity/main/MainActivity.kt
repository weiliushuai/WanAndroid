package com.example.administrator.wanandroid.ui.activity.main

import android.content.Intent
import android.support.v4.app.Fragment
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.base.BaseActivity
import com.example.administrator.wanandroid.ui.adapter.MainAdapter
import com.example.administrator.wanandroid.ui.fragment.classify.ClassifyFragment
import com.example.administrator.wanandroid.ui.fragment.home.HomeFragment
import com.example.administrator.wanandroid.ui.fragment.my.MyFragment
import android.support.v4.view.ViewPager
import android.widget.TextView
import com.example.administrator.wanandroid.ui.activity.search.SearchActivity
import com.example.administrator.wanandroid.utils.IconFontTextView

import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){


    private val fragments = ArrayList<Fragment>()
    private var mHomeFragment = HomeFragment()
    private var mClassifyFragment = ClassifyFragment()
    private var mMyFragment = MyFragment()
    private var adapter: MainAdapter?= null

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        ImmersionBar.with(this)
                .statusBarView(view)
                .init()
        fragments.add(mHomeFragment)
        fragments.add(mClassifyFragment)
        fragments.add(mMyFragment)
        setTextColor(home_icon,home_text)
        viewpage.currentItem = 0

        search_icon.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }

    }

    override fun listen() {
        viewpage.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
               when(position){
                   0 -> setTextColor(home_icon,home_text)
                   1 -> setTextColor(classfy_icon,classfy_text)
                   2 -> setTextColor(my_icon,my_text)
               }
            }
        })
        adapter = MainAdapter(supportFragmentManager,fragments)
        viewpage.adapter = adapter


        line_home.setOnClickListener {
            viewpage.currentItem = 0
            setTextColor(home_icon,home_text)
        }
        line_clasify.setOnClickListener {
            viewpage.currentItem = 1
            setTextColor(classfy_icon,classfy_text)
        }
        line_my.setOnClickListener {
            viewpage.currentItem = 2
            setTextColor(my_icon,my_text)
        }

    }

    fun setTextColor(text_Icon:IconFontTextView,text: TextView){
        home_icon.setTextColor(resources.getColor(R.color.color_969696))
        home_text.setTextColor(resources.getColor(R.color.color_969696))
        classfy_icon.setTextColor(resources.getColor(R.color.color_969696))
        classfy_text.setTextColor(resources.getColor(R.color.color_969696))
        my_icon.setTextColor(resources.getColor(R.color.color_969696))
        my_text.setTextColor(resources.getColor(R.color.color_969696))

        text_Icon.setTextColor(resources.getColor(R.color.color_2395FF))
        text.setTextColor(resources.getColor(R.color.color_2395FF))
    }

}


