package com.example.administrator.wanandroid.ui.activity.login

import com.example.administrator.wanandroid.base.BaseContract
import com.example.administrator.wanandroid.bean.LoginBean

/**
 * Created by： wls
 * Created in： 2018/3/20
 * Describption：
 */
interface LoginContract {

    interface View :BaseContract.BaseView{
        fun resultRegisterData(bean:LoginBean)

        fun resultLoginData(bean:LoginBean)
    }

    interface Presenter :BaseContract.BasePresenter<View>{

        fun getRegister(username:String,password:String)

        fun getLoginData(username:String,password:String)
    }

}