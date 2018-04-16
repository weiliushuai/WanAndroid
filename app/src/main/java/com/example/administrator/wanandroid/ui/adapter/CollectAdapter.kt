package com.example.administrator.wanandroid.ui.adapter

import android.content.Context
import android.text.Html
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.application.WAapplication
import com.example.administrator.wanandroid.bean.BaseBean
import com.example.administrator.wanandroid.bean.CollectBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import com.example.administrator.wanandroid.listener.CallObject
import com.example.administrator.wanandroid.ui.service.RetfortService
import com.example.administrator.wanandroid.utils.IconFontTextView
import org.jetbrains.anko.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
class CollectAdapter : BaseQuickAdapter<CollectBean.DataBean.DatasBean, BaseViewHolder> {


    private var context: Context?= null
    constructor(context: Context):super(R.layout.adapter_artical){
        this.context = context
    }
    override fun convert(helper: BaseViewHolder, item: CollectBean.DataBean.DatasBean) {
        helper!!.setText(R.id.auther,item.author)
                .setText(R.id.time,item.niceDate)
                .setText(R.id.title, Html.fromHtml(item.title))
                .setText(R.id.type,item.chapterName)
        var mColletion = helper.getView<IconFontTextView>(R.id.collect)

        helper!!.setText(R.id.collect, R.string.collect_se)
        helper!!.setTextColor(R.id.collect,context!!.resources.getColor(R.color.color_2395FF))

        mColletion.setOnClickListener {
            var unCollect_service = WAapplication.initRetroft().create(RetfortService::class.java)
            WAapplication.initSubscription().add(unCollect_service.unCollect_collect(item.id,item.originId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object:CallObject<BaseBean>(){
                        override fun onNext(bean: BaseBean) {
                           if(bean.errorCode == 0){
                               context?.toast("取消收藏")
                               data.removeAt(helper.layoutPosition)
                               if(helper.layoutPosition != 0){
                                   notifyItemRemoved(helper.layoutPosition)
                               }else{
                                   notifyDataSetChanged()
                               }
                           }else{
                               context?.toast("取消收藏失败")
                           }
                        }
                    }))
        }
    }

}