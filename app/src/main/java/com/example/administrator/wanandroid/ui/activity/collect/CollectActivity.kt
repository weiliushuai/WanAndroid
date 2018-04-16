package com.example.administrator.wanandroid.ui.activity.collect

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.base.BaseActivity
import com.example.administrator.wanandroid.bean.CollectBean
import com.example.administrator.wanandroid.listener.TitleInterface
import com.example.administrator.wanandroid.ui.adapter.ArticalAdapter
import com.example.administrator.wanandroid.ui.adapter.CollectAdapter
import com.example.administrator.wanandroid.utils.L
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_collect.*
import org.jetbrains.anko.toast

/**
 * Created by： wls
 * Created in： 2018/3/22
 * Describption：
 */
class CollectActivity :BaseActivity(),CollectContract.View, TitleInterface {


    private var mPresenter = CollectPresenter()
    private var mAdapter : CollectAdapter ?= null
    private var page:Int = 0

    override fun getContentView(): Int {
        return R.layout.activity_collect
    }

    override fun initView() {
        ImmersionBar.with(this).statusBarView(view1).init()
        mTitlabar.setTextView_title(R.string.collect_list)
        mTitlabar.setTextView_titleColor(R.color.color_white)
        mTitlabar.setTextLeftResourse(R.mipmap.icon_white_back)
        mTitlabar.mInterfaceTitle = this
        mPresenter.attachView(this)
        mPresenter.getCollectData(page)
        mAdapter = CollectAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

    }

    override fun addData() {
        mAdapter?.setOnLoadMoreListener( {
            mPresenter.getCollectData(page) },mRecyclerView)
    }

    override fun resultData(bean: CollectBean) {
        var pageCount = bean.data.pageCount

        mAdapter!!.addData(bean.data.datas)

        if(page > pageCount)
            mAdapter!!.loadMoreEnd()
        else
            mAdapter!!.loadMoreComplete()
        page++
    }

    override fun leftClick() {
        finish()
    }

    override fun rightClick() {}
}