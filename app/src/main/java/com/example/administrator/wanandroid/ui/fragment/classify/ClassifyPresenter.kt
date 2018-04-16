package com.example.administrator.wanandroid.ui.fragment.classify

import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.bean.ClassifyTabBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.service.RetfortService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by： wls
 * Created in： 2018/3/19
 * Describption：
 */
class ClassifyPresenter :ClassifyContract.Presenter{

    private var mView :ClassifyContract.View ?= null

    override fun attachView(view: ClassifyContract.View) {
        this.mView = view
    }

    override fun getTabData() {
        var tab_data = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(tab_data.getClassifyTab()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<ClassifyTabBean>(){
                    override fun onNext(bean: ClassifyTabBean) {
                        mView!!.resultTabData(bean)
                    }
                }))
    }
    override fun Tab_Artical(page:Int,cid: Int) {
       var tab_artical = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(tab_artical.getSelectTabArtical(page,cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<HomeArticalBean>(){
                    override fun onNext(bean: HomeArticalBean) {
                       mView!!.resultTabArtical(bean)
                    }

                }))
    }

}