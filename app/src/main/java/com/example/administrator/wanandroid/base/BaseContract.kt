package com.example.administrator.wanandroid.base

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
interface BaseContract {

    interface BasePresenter<T>{
        abstract fun attachView(view: T)
    }

    interface BaseView
}