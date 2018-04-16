package com.example.administrator.wanandroid.utils

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.listener.TitleInterface
import kotlinx.android.synthetic.main.view_titlebar.view.*

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
class TitleBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {


    internal var mlLinearLayout: View ?= null
    internal var context : Context?= null
    internal var mText_left : String ?= null
    internal var mText_right : String ?= null
    internal var mText_title : String ? = null
    internal var mTitle_back :Int ?= null
    internal var mShow_left : Boolean ?= null
    internal var mShow_right : Boolean ?= null
    internal var mShow_tilte : Boolean ?= null
    internal var mInterfaceTitle : TitleInterface ?= null


    init{
        this.context = context
        val mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        mShow_left = mTypeArray.getBoolean(R.styleable.TitleBar_show_left,false)
        mShow_right = mTypeArray.getBoolean(R.styleable.TitleBar_show_right,false)
        mShow_tilte = mTypeArray.getBoolean(R.styleable.TitleBar_show_title,false)
        mText_left = mTypeArray.getString(R.styleable.TitleBar_leftText)
        mText_right = mTypeArray.getString(R.styleable.TitleBar_rightText)
        mText_title = mTypeArray.getString(R.styleable.TitleBar_titleText)
        mTitle_back = mTypeArray.getColor(R.styleable.TitleBar_title_back, Color.BLUE)
        mlLinearLayout = LayoutInflater.from(context).inflate(R.layout.view_titlebar, this, true)

        initTextView()
        mTypeArray.recycle()
    }

    fun initTextView(){
        text_left.text = mText_left
        text_right.text = mText_right
        text_title.text = mText_title

        text_left.setOnClickListener(this)
        text_right.setOnClickListener(this)
    }

    /** 设置左边按钮图片 */
    fun setTextLeftResourse(resid:Int){
        val drawable = context!!.resources.getDrawable(resid)
        drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)
        text_left.setCompoundDrawables(drawable,null,null,null)
    }

    /** 设置右边按钮图片 */
    fun setTextRightResourse(resid:Int){
        val drawable = context!!.resources.getDrawable(resid)
        drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)
        text_right.setCompoundDrawables(null,null,drawable,null)
    }

    /** 设置左边文本 */
    fun setTextView_left(resid:Int){
        text_left.text = context!!.resources.getString(resid)
    }

    /** 设置右边文本 */
    fun setTextView_right(resid:Int){
        text_right.text = context!!.resources.getString(resid)
    }

    /** 设置标题 */
    fun setTextView_title(resid: Int){
        text_title.text = context!!.resources.getString(resid)
    }

    fun setTextView_title(str: String){
        text_title.text = str
    }

    fun setTextView_titleColor(color:Int){
        text_title.setTextColor(resources.getColor(color))
    }

    override fun onClick(v: View) {
       when(v.id){
           R.id.text_left ->{ mInterfaceTitle!!.leftClick()}
           R.id.text_right ->{ mInterfaceTitle!!.rightClick()}
       }
    }

    fun setTitleInterface(click : TitleInterface){
        this.mInterfaceTitle = click
    }


}