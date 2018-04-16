package com.example.administrator.wanandroid.utils

import android.content.Context
import android.support.annotation.StringRes
import android.view.Gravity
import android.widget.Toast

import java.util.Timer
import java.util.TimerTask


/**
 * Toast的一些封装方法
 *
 */
class ToastUtils private constructor() // 私有化构造
{
    private var mContext: Context? = null // 上下文对象

    private val duration = 2500
    private var mToast: Toast? = null

    private object Helper { // 内部帮助类，实现单例
        internal val INSTANCE = ToastUtils()
    }

    fun showToast(@StringRes strResID: Int) { // 根据资源id弹Toast
        if (mContext == null) {
            throw RuntimeException("Please init the Context before showToast")
        }
        showToast(mContext!!.resources.getText(strResID))
    }

    fun showToast(str: CharSequence) { // 根据字符串弹Toast
        if (mContext == null) {
            throw RuntimeException("Please init the Context before showToast")
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, str, Toast.LENGTH_LONG)
        } else {
            mToast!!.setText(str)
        }

        mToast!!.setGravity(Gravity.CENTER, 0, 0)
        showMyToast(mToast!!)

    }

    /**
     * 设置显示时长
     * Toast自带： LONG_DELAY = 3500; SHORT_DELAY = 2000
     * 第一个Timer是每隔3s执行一次，此时，Toast还在显示（LENGTH_LONG）
     * 使得toast一直在显示
     * 后一个时间是显示的时间。即延时2.0s后，只想toast，cancel()；
     */
    fun showMyToast(toast: Toast) {

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                toast.show()
            }
        }, 0, 3000)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                toast.cancel()
                timer.cancel()
            }
        }, 2000)

    }

    companion object {

        // 获取单例对象
        val instance: ToastUtils
            get() = Helper.INSTANCE

        fun init(context: Context) { // 初始化Context
            Helper.INSTANCE.mContext = context
        }
    }


}
