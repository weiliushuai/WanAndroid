package com.example.administrator.wanandroid.ui.activity.collect

import com.example.administrator.wanandroid.base.BaseContract
import com.example.administrator.wanandroid.bean.CollectBean

/**
 * Created by： wls
 * Created in： 2018/3/22
 * Describption：
 */
interface CollectContract {

    interface View : BaseContract.BaseView{
        fun resultData(bean: CollectBean)
    }

    interface Presenter : BaseContract.BasePresenter<View>{
        fun getCollectData(page:Int)
    }
}