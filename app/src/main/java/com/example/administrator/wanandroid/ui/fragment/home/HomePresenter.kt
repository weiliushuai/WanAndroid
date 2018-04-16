package com.example.administrator.wanandroid.ui.fragment.home

import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.bean.BaseBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import rx.schedulers.Schedulers
import com.example.administrator.wanandroid.bean.HomeBannerBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.service.RetfortService
import com.example.administrator.wanandroid.utils.L
import rx.android.schedulers.AndroidSchedulers


/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
class HomePresenter :HomeContract.Presenter{

    private var view:HomeContract.View ?= null

    override fun attachView(view: HomeContract.View) {
       this.view = view
    }

    /** banner的请求 */
    override fun getBannerData() {
        var banner_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(banner_service.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: CallObject<HomeBannerBean>() {
                    override fun onNext(t: HomeBannerBean) {
                       view!!.resultBannerData(t)
                    }

                }))
    }

    /** 首页列表的请求 */
    override fun getArticalData(page:Int) {
       var artical_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(artical_service.getArtical(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: CallObject<HomeArticalBean>(){
                    override fun onNext(bean: HomeArticalBean) {
                       view!!.resultArticalData(bean)
                    }

                }))
    }


}