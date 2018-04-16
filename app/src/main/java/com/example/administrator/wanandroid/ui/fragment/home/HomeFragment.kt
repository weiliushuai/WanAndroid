package com.example.administrator.wanandroid.ui.fragment.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.provider.SyncStateContract
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.base.BaseFragment
import com.example.administrator.wanandroid.bean.BaseBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import com.example.administrator.wanandroid.bean.HomeBannerBean
import com.example.administrator.wanandroid.ui.activity.web.WebViewActivity
import com.example.administrator.wanandroid.ui.adapter.ArticalAdapter
import com.example.administrator.wanandroid.ui.weight.GlideImageLoader
import com.example.administrator.wanandroid.url.Constants
import com.example.administrator.wanandroid.utils.L
import com.example.administrator.wanandroid.utils.SharedPref
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast


/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
class HomeFragment :BaseFragment(),HomeContract.View, OnBannerListener {


    private var mPresenter = HomePresenter()
    private var imageList = ArrayList<String>()
    private var urlList = ArrayList<String>()
    private var itemUrls = ArrayList<String>()
    private var mAdapter: ArticalAdapter ?= null
    private var page:Int = 0
    private var mBanner : Banner ?= null
    private var position :Int = 0
    override fun setContentView(): Int {
       return R.layout.fragment_home
    }

    override fun initView(view: View) {
        mPresenter.attachView(this)
        mPresenter.getBannerData()
        mPresenter.getArticalData(page)
        mAdapter = ArticalAdapter(context!!)
        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mRecyclerView.adapter = mAdapter
        var layout = LayoutInflater.from(activity).inflate(R.layout.banner_layout,mRecyclerView,false)
        mBanner = layout.findViewById<Banner>(R.id.banner)
        mAdapter!!.setHeaderView(layout)//给RecyclerView添加头部控件
        mRefresh.isRefreshing = true
        Handler().postDelayed({
            mRefresh.isRefreshing = false
        },2000)
        mRefresh.setOnRefreshListener {
            page = 0
            mPresenter.getArticalData(page)
        }
    }

    /** 得到轮播图请求数据 */
    override fun resultBannerData(bannerBean: HomeBannerBean) {
        imageList.clear()
        urlList.clear()
         for (bean in bannerBean.data){
             imageList.add(bean.imagePath)
             urlList.add(bean.url)
         }

        mBanner!!.setImageLoader(GlideImageLoader())
        mBanner!!.setImages(imageList)
        mBanner!!.isAutoPlay(true)
        //设置轮播时间
        mBanner!!.setDelayTime(2000)
        mBanner!!.setOnBannerListener(this)
        mBanner!!.start()
    }

    /** mAdapter的监听 */
    override fun listen() {

        mAdapter!!.setOnLoadMoreListener({
            mPresenter.getArticalData(page)
        }
        ,mRecyclerView)

        mAdapter!!.setOnItemClickListener { _, _, position ->
            var intent = Intent(activity,WebViewActivity::class.java)
            var bundle = Bundle()
            bundle.putString("url",itemUrls[position])
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    /** 文章列表数据 */
    override fun resultArticalData(bean: HomeArticalBean) {

        mRefresh.isRefreshing = false//请求成功，刷新按钮隐藏
        var totalPage = bean.data.pageCount
        for(itembean in bean.data.datas){
            itemUrls.add(itembean.link)
        }
        mAdapter!!.setNewData(bean.data.datas)
        if(page < totalPage)
            mAdapter!!.loadMoreComplete()
        else
            mAdapter!!.loadMoreEnd()
        page++
    }


    override fun onStart() {
        super.onStart()
        mBanner!!.isAutoPlay(true)
    }

    //轮播图的点击事件
    override fun OnBannerClick(position: Int) {
        var intent = Intent(activity,WebViewActivity::class.java)
        var bundle = Bundle()
        bundle.putString("url",urlList[position])
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        mBanner!!.stopAutoPlay()
    }

}