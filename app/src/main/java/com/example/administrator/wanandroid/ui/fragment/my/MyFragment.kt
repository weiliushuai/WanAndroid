package com.example.administrator.wanandroid.ui.fragment.my

import android.content.Intent
import android.provider.SyncStateContract
import android.view.View
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.base.BaseFragment
import com.example.administrator.wanandroid.ui.activity.collect.CollectActivity
import com.example.administrator.wanandroid.ui.activity.login.LoginActivity
import com.example.administrator.wanandroid.url.Constants
import com.example.administrator.wanandroid.utils.SharedPref
import com.example.administrator.wanandroid.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_my.*

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
class MyFragment :BaseFragment(){
    override fun setContentView(): Int {
        return R.layout.fragment_my
    }

    override fun initView(view: View) {
        ToastUtils.init(context!!)
        if(SharedPref(context!!).getBoolean(Constants.Code.IS_LOGIN,false)){
            user_name.text = SharedPref(context!!).getString(Constants.Code.USERNAME,"USER_NAME")
            login.text = mActivity!!.resources.getString(R.string.exit_login)
        }else{
            user_name.text = context!!.resources.getString(R.string.no_login)
            login.text = mActivity!!.resources.getString(R.string.login_now)

        }
        my_collection.setOnClickListener{
            if(SharedPref(context!!).getBoolean(Constants.Code.IS_LOGIN,false)){
                startActivity(Intent(mActivity!!,CollectActivity::class.java))
            }else{
                ToastUtils.instance.showToast(R.string.please_login)
            }
        }
        about_us.setOnClickListener {  }

        login.setOnClickListener {
            if(SharedPref(context!!).getBoolean(Constants.Code.IS_LOGIN,false)){
                login.text = mActivity!!.resources.getString(R.string.login_now)
                user_name.text = context!!.resources.getString(R.string.no_login)
                SharedPref(context!!).putBoolean(Constants.Code.IS_LOGIN,false)
                ToastUtils.instance.showToast(R.string.have_loginout)
            }else{
                startActivity(Intent(mActivity,LoginActivity::class.java))
            }
        }
    }
}