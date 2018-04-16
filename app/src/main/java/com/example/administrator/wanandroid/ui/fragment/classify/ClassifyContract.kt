package com.example.administrator.wanandroid.ui.fragment.classify

import com.example.administrator.wanandroid.base.BaseContract
import com.example.administrator.wanandroid.bean.ClassifyTabBean
import com.example.administrator.wanandroid.bean.HomeArticalBean

/**
 * Created by： wls
 * Created in： 2018/3/19
 * Describption：
 */
interface ClassifyContract {

    interface View :BaseContract.BaseView{
        fun resultTabData(bean: ClassifyTabBean)

        fun resultTabArtical(bean: HomeArticalBean)
    }

    interface Presenter:BaseContract.BasePresenter<View>{
        fun getTabData()

        fun Tab_Artical(page:Int,cid:Int)
    }
}