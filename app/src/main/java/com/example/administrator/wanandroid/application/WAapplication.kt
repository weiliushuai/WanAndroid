package com.example.administrator.wanandroid.application

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.example.administrator.wanandroid.url.Constants
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */
class WAapplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    /**
     * 扩展函数
     * Context：接收对象
     * toast:扩展函数的函数名
     * （）里面为要传的参数，可以不传
     */
    fun Context.toast(message:CharSequence){
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()
    }

    companion object {
        var context:Context ?= null
        fun okHttp() :OkHttpClient{
            val  cookiejar = PersistentCookieJar(SetCookieCache(),SharedPrefsCookiePersistor(context))
            var okClient = OkHttpClient.Builder()
                    .readTimeout(1000L,TimeUnit.MILLISECONDS)
                    .connectTimeout(1000L,TimeUnit.MILLISECONDS)
                    .cookieJar(cookiejar)
                    .build()
            return okClient
        }


        /** 配置Retrofit网络请求 */
        fun initRetroft():Retrofit{
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.URL.Host)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttp())
                    .build()
            return retrofit
        }


        @JvmStatic
        fun initSubscription(): CompositeSubscription {
            val subscription = CompositeSubscription()
            return subscription
        }

    }
}