package com.example.administrator.wanandroid.listener


import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.utils.L
import com.example.administrator.wanandroid.utils.ToastUtils
import rx.Observer

/**
 * Created by： wls
 * Created in： 2018/3/15
 * Describption：
 */
abstract class CallObject<T>: Observer<T> {

    override fun onCompleted() {
    }

    override fun onError(e: Throwable) {
        L.Companion.d(e!!.message.toString())

      //  ToastUtils.instance.showToast(R.string.no_net)
    }
}