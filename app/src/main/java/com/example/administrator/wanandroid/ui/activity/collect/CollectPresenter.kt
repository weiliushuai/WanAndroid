package com.example.administrator.wanandroid.ui.activity.collect

import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.bean.CollectBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.service.RetfortService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by： wls
 * Created in： 2018/3/22
 * Describption：
 */
class CollectPresenter : CollectContract.Presenter{

    private var mView:CollectContract.View ?= null

    override fun attachView(view: CollectContract.View) {
       this.mView = view
    }

    override fun getCollectData(page: Int) {

        var collect = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(collect.getCollectList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<CollectBean>(){
                    override fun onNext(bean: CollectBean) {
                        mView?.resultData(bean)
                    }
                }))
    }
}