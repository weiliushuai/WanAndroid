package com.example.administrator.wanandroid.base



import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gyf.barlibrary.ImmersionBar

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */

open abstract class BaseFragment : Fragment(),BaseContract.BaseView {
    var mImmersionBar:ImmersionBar ?= null
    var rootView : View ?= null
    var mActivity : BaseActivity ?= null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if(context is BaseActivity){
            var activity = context;
            mActivity = activity
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(setContentView(),container,false)

        return rootView
    }

    //紧跟onCreateView方法执行
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(setImmersionBar())
            initmImmersionBar()
        initView(view)
        addData()
        listen()
    }


    abstract fun setContentView():Int

    //用open标记，说明他可以被重写
    open fun initView(view:View){}

    open fun addData(){}

    open fun listen(){}

    //初始化沉浸式状态栏
    fun initmImmersionBar(){
        mImmersionBar = ImmersionBar.with(activity!!)
        mImmersionBar!!.init()
    }

    //是否设置状态栏
    fun setImmersionBar() : Boolean{
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if(mImmersionBar != null){
            mImmersionBar!!.destroy()
        }
    }
}