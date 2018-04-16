package com.example.administrator.wanandroid.ui.activity.login

import android.content.Intent
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.administrator.wanandroid.R
import com.example.administrator.wanandroid.base.BaseActivity
import com.example.administrator.wanandroid.bean.LoginBean
import com.example.administrator.wanandroid.ui.activity.main.MainActivity
import com.example.administrator.wanandroid.url.Constants
import com.example.administrator.wanandroid.utils.L
import com.example.administrator.wanandroid.utils.SharedPref
import com.example.administrator.wanandroid.utils.ToastUtils
import com.gyf.barlibrary.ImmersionBar
import kotlinx.android.synthetic.main.activity_login.*
/**
 * Created by： wls
 * Created in： 2018/3/20
 * Describption：
 */
class LoginActivity : BaseActivity(),LoginContract.View, View.OnClickListener{


    private var mPresenter = LoginPresenter()
    private var username :String? = null
    private var password :String? = null
    override fun getContentView(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        ImmersionBar.with(this).statusBarView(view1).init()
        mPresenter.attachView(this)


        edit_user_name.addTextChangedListener(mTextWatcher)
        edit_password.addTextChangedListener(mTextWatcher)

        ToastUtils.init(this)

        close.setOnClickListener(this)
        btn_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    internal var mTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if(edit_user_name.hasFocus()){
                username = s.toString()
            }else if(edit_password.hasFocus()){
                password = s.toString()
            }
        }
        override fun afterTextChanged(s: Editable) {}
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_register ->{

                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    ToastUtils.instance.showToast(R.string.null_name_pass)
                }else if(username!!.length < 6 || password!!.length < 6){
                    ToastUtils.instance.showToast(R.string.length_short)
                }else{
                    mPresenter.getRegister(username!!,password!!)
                }

            }
            R.id.btn_login ->{
                if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
                    ToastUtils.instance.showToast(R.string.null_name_pass)
                }else if(username!!.length < 6 || password!!.length < 6){
                    ToastUtils.instance.showToast(R.string.length_short)
                }else{
                    mPresenter.getLoginData(username!!,password!!)
                }

            }
            R.id.close ->finish()

        }


    }

    /** 登录成功 */
    override fun resultLoginData(bean: LoginBean) {
        ToastUtils.instance.showToast(R.string.login_success)
        SharedPref(this).putString(Constants.Code.USERNAME,bean.data.username)
        SharedPref(this).putString(Constants.Code.PASSWORD,bean.data.password)
        SharedPref(this).putBoolean(Constants.Code.IS_LOGIN,true)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }

    /** 注册成功 */
    override fun resultRegisterData(bean: LoginBean) {
        ToastUtils.instance.showToast(R.string.register_success)
        SharedPref(this).putString(Constants.Code.USERNAME,bean.data.username)
        SharedPref(this).putString(Constants.Code.PASSWORD,bean.data.password)
        SharedPref(this).putBoolean(Constants.Code.IS_LOGIN,true)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}