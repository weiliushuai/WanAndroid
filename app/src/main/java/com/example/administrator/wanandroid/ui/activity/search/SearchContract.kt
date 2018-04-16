package com.example.administrator.wanandroid.ui.activity.search

import com.example.administrator.wanandroid.base.BaseContract
import com.example.administrator.wanandroid.bean.SearchBean
import com.example.administrator.wanandroid.bean.SearchDataBean

/**
 * Created by： wls
 * Created in： 2018/3/16
 * Describption：
 */
interface SearchContract :BaseContract{
    interface View :BaseContract.BaseView{
        fun resultData(bean: SearchBean)

        fun resultSearchData(bean: SearchDataBean)
    }

    interface Presenter : BaseContract.BasePresenter<View>{
        fun getKeyBoardData()

        fun getSearchData(page:Int,key:String)
    }
}