package com.example.administrator.wanandroid.ui.activity.search

import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.bean.SearchBean
import com.example.administrator.wanandroid.bean.SearchDataBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.service.RetfortService

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by： wls
 * Created in： 2018/3/16
 * Describption：
 */
class SearchPresenter :SearchContract.Presenter{

    private var mView : SearchContract.View ?= null

    override fun attachView(view: SearchContract.View) {
       this.mView = view
    }
    /** 得到热门搜索 */
    override fun getKeyBoardData() {
       var retfort_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(retfort_service.getKeyboard()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<SearchBean>(){
                    override fun onNext(bean: SearchBean) {
                       mView!!.resultData(bean)
                    }

                }))
    }
    /** 搜索数据 */
    override fun getSearchData(page: Int,key:String) {
        var retfort_servicedata = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(retfort_servicedata.getSearchdata(page,key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<SearchDataBean>(){
                    override fun onNext(bean: SearchDataBean) {
                        mView!!.resultSearchData(bean)
                    }

                }))
    }

}