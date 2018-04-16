package com.example.administrator.wanandroid.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * @user  lqm
 * @desc  使用IConFont TextView
 */
class IconFontTextView : TextView {

    private var mContext: Context? = null

    constructor(context: Context) : super(context) {
        mContext = context
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
        initView()
    }

    private fun initView() {
        val iconfont = Typeface.createFromAsset(mContext!!.assets, "iconfont.ttf")
        typeface = iconfont
    }
}
