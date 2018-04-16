package com.example.administrator.wanandroid.ui.activity.search


import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.R.id.*
import com.example.administrator.wanandroid.base.BaseActivity
import com.example.administrator.wanandroid.bean.SearchBean
import com.example.administrator.wanandroid.bean.SearchDataBean
import com.example.administrator.wanandroid.ui.adapter.ArticalAdapter
import com.example.administrator.wanandroid.utils.L
import com.gyf.barlibrary.ImmersionBar
import com.library.flowlayout.FlowLayoutManager
import kotlinx.android.synthetic.main.search_layout.*
import kotlinx.android.synthetic.main.tab_layout.view.*
import org.jetbrains.anko.find

/**
 * Created by： wls
 * Created in： 2018/3/16
 * Describption：
 */
class SearchActivity :BaseActivity(),SearchContract.View{


    private var mPresent = SearchPresenter()
    private var keyBoardList = ArrayList<String>()
    private var mAdapter:ArticalAdapter ?= null
    private var page:Int = 0
    override fun getContentView(): Int {
        return R.layout.search_layout
    }

    override fun initView() {
        ImmersionBar.with(this)
                .statusBarView(view)
                .init()
        mPresent.attachView(this)

        mPresent.getKeyBoardData()

        if(!TextUtils.isEmpty(mSearchView.text)){
            edit_clear.visibility = View.VISIBLE
            mPresent.getSearchData(page,mSearchView.text.toString())
        }else{
            edit_clear.visibility = View.GONE
        }

        mAdapter = ArticalAdapter(this)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

    }

    override fun addData() {
        //加载更多
        mAdapter!!.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            mPresent.getSearchData(page,mSearchView.text.toString())
        },mRecyclerView)

        //点击按钮，搜索框清空
        edit_clear.setOnClickListener {
            mSearchView.setText("")
            edit_clear.visibility = View.GONE
        }
        //取消按钮的点击事件
        mText_cancel.setOnClickListener { finish() }

        mSearchView.addTextChangedListener(mTextWatch)
    }

    /** 搜索框的监听 */
    private var mTextWatch = object:TextWatcher{
        override fun afterTextChanged(s: Editable) {}

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if(!TextUtils.isEmpty(s)){
                edit_clear.visibility = View.VISIBLE
                mPresent.getSearchData(page,s.toString())
            }else{
                edit_clear.visibility = View.GONE
            }
        }

    }


    override fun resultData(bean: SearchBean) {
        for(item in bean.data){
            keyBoardList.add(item.name)
        }
        mFlayout_Group.removeAllViews()
        for(index in keyBoardList.indices){
            var text_key = LayoutInflater.from(this).inflate(R.layout.search_adapter,mFlayout_Group,
                    false) as TextView
            text_key.text = keyBoardList[index]
            mFlayout_Group.addView(text_key)
            text_key.setOnClickListener {
                mSearchView.setText(text_key.text.toString())
                edit_clear.visibility = View.VISIBLE
                mPresent.getSearchData(page,text_key.text.toString())

            }
        }
    }

    override fun resultSearchData(bean: SearchDataBean) {
        var pageCount = bean.data.pageCount
        if(pageCount > 0){
            mRecyclerView.visibility = View.VISIBLE
            text_hot.visibility = View.GONE
            mFlayout_Group.visibility = View.GONE
        }
        mAdapter!!.addData(bean.data.datas)

        if(page > pageCount)
            mAdapter!!.loadMoreEnd()
        else
            mAdapter!!.loadMoreComplete()
        page++
    }
}