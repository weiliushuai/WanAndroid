package com.example.administrator.wanandroid.ui.activity.web

import android.view.KeyEvent
import android.view.View
import android.webkit.*
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.base.BaseActivity
import com.example.administrator.wanandroid.listener.TitleInterface
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.web_layout.*
/**
 * Created by： wls
 * Created in： 2018/3/16
 * Describption：
 */
class WebViewActivity :BaseActivity(){
    private var title :String ?= null
    private var url :String ?= null
    override fun getContentView(): Int {
        return R.layout.web_layout
    }

    override fun initView() {
        ImmersionBar.with(this)
                .statusBarView(view1)
                .init()
        titlebar.setTextLeftResourse(R.mipmap.icon_white_back)
        titlebar.apply {
            setTitleInterface(object :TitleInterface{
                override fun leftClick() {
                   finish()
                }
                override fun rightClick() {}
            })
        }

        var bundle = this.intent.extras

        url = bundle.getString("url").toString()

        var webSetting = mWebView.settings
        //是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webSetting.javaScriptEnabled = true
        //设置js可以直接打开窗口，如window.open()，默认为false
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        //将图片调整到适合webview的大小
        webSetting.useWideViewPort = true
        //缩放至屏幕大小
        webSetting.loadWithOverviewMode = true
        webSetting.builtInZoomControls = true
        //支持缩放
        webSetting.setSupportZoom(true)
    }

    override fun addData() {
       mWebView.webViewClient = webViewClient
       mWebView.loadUrl(url)
       mWebView.webChromeClient = webChromeClient
    }

    private var webViewClient = object:WebViewClient(){
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            mWebView.loadUrl(url)
            return true
        }
    }

    private var webChromeClient = object:WebChromeClient(){
        //标题
        override fun onReceivedTitle(view: WebView?, title: String) {
            super.onReceivedTitle(view, title)
            if(titlebar != null){
                titlebar.setTextView_title(title)
                titlebar.setTextView_titleColor(R.color.color_white)
            }
        }
        //加载进度条
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if(newProgress == 100){
                progressbar.visibility = View.GONE
            }else{
                progressbar.visibility = View.VISIBLE
                progressbar.progress = newProgress
            }
        }
    }

    //返回上页面
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
            mWebView.goBack()
        }
        return true
    }
}