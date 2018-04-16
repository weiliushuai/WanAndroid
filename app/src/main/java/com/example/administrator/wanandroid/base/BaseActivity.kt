package com.example.administrator.wanandroid.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar


/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
abstract class BaseActivity : FragmentActivity(),BaseContract.BaseView {

    var mImmersionBar:ImmersionBar ?= null
    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        if(setImmersionBar())
            initmImmersionBar()


        initView()
        addData()
        listen()
    }

     protected abstract fun getContentView(): Int

    //用open标记，说明他可以被重写
     open fun initView(){}//初始化
     open fun addData(){}//加载数据
     open fun listen(){}//监听

    //初始化沉浸式状态栏
    fun initmImmersionBar(){
        mImmersionBar = ImmersionBar.with(this)
        mImmersionBar!!.init()
    }

    //是否设置状态栏
    fun setImmersionBar() : Boolean{
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if(mImmersionBar != null)
            mImmersionBar!!.destroy()
    }

}