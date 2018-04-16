package com.example.administrator.wanandroid.ui.activity.login

import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.bean.LoginBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.service.RetfortService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by： wls
 * Created in： 2018/3/20
 * Describption：
 */
class LoginPresenter :LoginContract.Presenter{

    private var mView : LoginContract.View ?= null
    override fun attachView(view: LoginContract.View) {
       this.mView = view
    }
    override fun getRegister(username: String, password: String) {

        var register_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(register_service.getRegister(username,password,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<LoginBean>(){
                    override fun onNext(bean: LoginBean) {
                       mView!!.resultRegisterData(bean)
                    }

                }))
    }

    override fun getLoginData(username: String, password: String) {
        var login_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(login_service.getLogin(username,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<LoginBean>(){
                    override fun onNext(bean: LoginBean) {
                      mView!!.resultLoginData(bean)
                    }

                }))
    }
}