package com.example.administrator.wanandroid.ui.fragment.classify

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.R.id.mFlayout_Group
import com.example.administrator.wanandroid.R.id.mRecyclerView
import com.example.administrator.wanandroid.base.BaseFragment
import com.example.administrator.wanandroid.bean.ClassifyTabBean
import com.example.administrator.wanandroid.bean.HomeArticalBean
import com.example.administrator.wanandroid.ui.activity.web.WebViewActivity
import com.example.administrator.wanandroid.ui.adapter.ArticalAdapter
import com.example.administrator.wanandroid.utils.FlayoutGroup
import com.example.administrator.wanandroid.utils.L
import kotlinx.android.synthetic.main.fragment_classify.*

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
class ClassifyFragment :BaseFragment(),ClassifyContract.View{
    private val mPresenter = ClassifyPresenter()
    private var mPopupWindow = PopupWindow()
    private var count:Int = 0
    private var mFlayout_Group: FlayoutGroup?= null
    private var position:Int = 0
    private var mAdapter :ArticalAdapter ?= null
    private var page:Int = 0//页码
    private var cid:Int = 0//标签的id，用于请求该标签下的文章列表
    private var urlList  = ArrayList<String>()//文章的详情连接集合
    private var oldindex :Int = 0//上一次选中的标签下标
    private var init :Int = 0//初始状态下，确定第一个tab的第一个标签是否是选中装填
    override fun setContentView(): Int {
        return R.layout.fragment_classify
    }

    /** 数据初始化 */
    override fun initView(view: View) {
        showPopWindow()
        mPresenter.attachView(this)
        mPresenter.getTabData()
        mRecyclerView.layoutManager = LinearLayoutManager(mActivity)
        mAdapter = ArticalAdapter(context!!)
        mRecyclerView.adapter = mAdapter
    }

    /** 获得tab列表 */
    override fun resultTabData(bean: ClassifyTabBean) {
        for(item in bean.data){
            mTablayout.addTab(mTablayout.newTab().setText(item.name))
        }
        var c = 0//用于判断第一次点击的tab是不是第一个tab
        //初始状态显示第一个tab的第一个标签
        changeSelectTab(bean.data[position].children[0].id)

        mTablayout.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
           override fun onTabReselected(tab: TabLayout.Tab) {
               if(count%2 == 1){
                   mPopupWindow.showAsDropDown(mTablayout)
               }else{
                   mPopupWindow.dismiss()
               }
               if(tab.position == 0 && count == 0 && c == 0){
                   c = 1
                   mPopupWindow.showAsDropDown(mTablayout)
                   count = 1
                   position = 0
                   addViewToFlayout(bean)
               }
               count++
           }
           override fun onTabUnselected(tab: TabLayout.Tab) {}

           override fun onTabSelected(tab: TabLayout.Tab) {
              count = 0
              mPopupWindow.showAsDropDown(mTablayout)
              position = tab.position
              addViewToFlayout(bean)
           }
       })
    }

    /** PopupWindow用于展示tab下的标签 */
    fun showPopWindow(){
        var mLayout = LayoutInflater.from(mActivity).inflate(R.layout.popup_layout,null)
        mPopupWindow.contentView = mLayout
        mPopupWindow.width = ViewGroup.LayoutParams.MATCH_PARENT
        mPopupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
        mPopupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mFlayout_Group = mLayout.findViewById(R.id.mFlayout_Group)

    }

    /** 以流式布局添加各个tab下的标签 */
    fun addViewToFlayout(bean: ClassifyTabBean){
        mFlayout_Group!!.removeAllViews()
        for(index in bean.data[position].children.indices){
            var text_key = LayoutInflater.from(mActivity).inflate(R.layout.search_adapter,
                    mFlayout_Group,false) as TextView
            text_key.text = bean.data[position].children[index].name
            mFlayout_Group!!.addView(text_key)
            if(index ==0 && position == 0 && init == 0){//初始状态下，第一个tab的第一个标签变色
                text_key.setTextColor(resources.getColor(R.color.color_white))
                text_key.setBackgroundResource(R.drawable.shape_text_select)
            }
            text_key.setOnClickListener {
                //伴随着PopupWindow的显示与隐藏，count值每次加1
                count++
                mPopupWindow.dismiss()
                init++//init发生变化，说明不是初始状态，第一个tab的第一个标签不再保持选中状态
                if(index != oldindex){//每次只能选中一个标签
                  var text =   mFlayout_Group!!.getChildAt(oldindex) as TextView
                  text.setTextColor(resources.getColor(R.color.color_666666))
                  text.setBackgroundResource(R.drawable.shape_text)
                }
                text_key.setTextColor(resources.getColor(R.color.color_white))
                text_key.setBackgroundResource(R.drawable.shape_text_select)
                changeSelectTab(bean.data[position].children[index].id)
                oldindex = index
            }
        }
    }

    /** 选择标签后，进行数据的请求 */
    fun changeSelectTab(cid:Int){
        page = 0
        this.cid = cid
        mPresenter.Tab_Artical(page,cid)
    }

    /** mAdapter的监听事件 */
    override fun listen() {
        mAdapter!!.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(mActivity,WebViewActivity::class.java)
            val bundle = Bundle()
            bundle.putString("url",urlList[position])
            intent.putExtras(bundle)
            startActivity(intent)
        }
        mAdapter!!.setOnLoadMoreListener({mPresenter.Tab_Artical(page,cid) },mRecyclerView)
    }

    /** 标签下的文章列表 */
    override fun resultTabArtical(bean: HomeArticalBean) {
       val pageCount = bean.data.pageCount
        for(itembean in bean.data.datas){
            urlList.add(itembean.link)
        }
        if(page == 0){//切换到新的标签时，更换数据
            mAdapter!!.setNewData(bean.data.datas)
        }else{//分页时，添加数据
            mAdapter!!.addData(bean.data.datas)
        }
        if(page > pageCount)
            mAdapter!!.loadMoreEnd()
        else
            mAdapter!!.loadMoreComplete()
        page++
    }

    /** 页面不可见时，隐藏PopupWindow */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(!isVisibleToUser && mPopupWindow.isShowing) {
            mPopupWindow.dismiss()
            count++
        }
    }

}