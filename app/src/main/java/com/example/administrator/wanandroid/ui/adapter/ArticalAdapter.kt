package com.example.administrator.wanandroid.ui.adapter

import android.content.Context
import android.content.Intent
import android.provider.SyncStateContract
import android.text.Html
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.R.id.view
import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.application.WAapplication.Companion.context
import com.example.administrator.wanandroid.bean.BaseBean
import com.example.administrator.wanandroid.bean.CollectBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.activity.login.LoginActivity
import com.example.administrator.wanandroid.ui.service.RetfortService
import com.example.administrator.wanandroid.url.Constants
import com.example.administrator.wanandroid.utils.IconFontTextView
import com.example.administrator.wanandroid.utils.SharedPref
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
class ArticalAdapter :BaseQuickAdapter<HomeArticalBean.DataBean.DatasBean,BaseViewHolder>{


    private var context:Context ?= null

    constructor(context: Context):super(R.layout.adapter_artical){
        this.context = context
    }


    override fun convert(helper: BaseViewHolder, item: HomeArticalBean.DataBean.DatasBean) {
        helper!!.setText(R.id.auther,item.author)
                .setText(R.id.time,item.niceDate)
                .setText(R.id.title,Html.fromHtml(item.title))
                .setText(R.id.type,item.chapterName)

        var iconCollect = helper?.getView<IconFontTextView>(R.id.collect)

        iconCollect.setOnClickListener {
            if(SharedPref(context!!).getBoolean(Constants.Code.IS_LOGIN,false)){
                if(item.isCollect){
                    unCollet(item,helper)
                }else{//收藏
                    isCollect(item,helper)
                }
            }else{
                context?.toast("请先登录！")
                context?.startActivity(Intent(context!!,LoginActivity::class.java))
            }
        }

        if(item.isCollect){
            helper!!.setText(R.id.collect,R.string.collect_se)
            helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_2395FF))

        }else{
            helper!!.setText(R.id.collect,R.string.collect_nor)
            helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_969696))
        }

    }

    /** 收藏 */
    fun isCollect(item: HomeArticalBean.DataBean.DatasBean,helper: BaseViewHolder){
        var collectArtial_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(collectArtial_service.getArticals(item.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: CallObject<BaseBean>(){
                    override fun onNext(bean: BaseBean) {
                        if(bean.errorCode == 0){
                            context?.toast("收藏成功")
                            item.isCollect = true
                            helper!!.setText(R.id.collect,R.string.collect_se)
                            helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_2395FF))

                        }else{
                            context?.toast("收藏失败")
                            item.isCollect = false
                            helper!!.setText(R.id.collect,R.string.collect_nor)
                            helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_969696))
                        }
                    }

                }))
    }

    fun unCollet(item: HomeArticalBean.DataBean.DatasBean,helper: BaseViewHolder){
        var unCollect_service = WAapplication.initRetroft().create(RetfortService::class.java)
        WAapplication.initSubscription().add(unCollect_service.unCollect_Artical(item.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object:CallObject<BaseBean>(){
                    override fun onNext(bean: BaseBean) {
                       if(bean.errorCode == 0){
                           context?.toast("取消收藏")
                           item.isCollect = false
                           helper!!.setText(R.id.collect,R.string.collect_nor)
                           helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_969696))
                       }else{
                           context?.toast("取消收藏失败")
                           item.isCollect = true
                           helper!!.setText(R.id.collect,R.string.collect_se)
                           helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_2395FF))
                       }
                    }

                }))
    }

}