package com.example.administrator.wanandroid.ui.fragment.home

import com.example.administrator.wanandroid.base.BaseContract
import com.example.administrator.wanandroid.bean.BaseBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import com.example.administrator.wanandroid.bean.HomeBannerBean

/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
interface HomeContract:BaseContract {

    interface View:BaseContract.BaseView{
        fun resultBannerData(bannerBean: HomeBannerBean)

        fun resultArticalData(bean: HomeArticalBean)

    }
    interface Presenter:BaseContract.BasePresenter<View>{
        fun getBannerData()

        fun getArticalData(page:Int)

    }
}