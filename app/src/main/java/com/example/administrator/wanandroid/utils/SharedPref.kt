package com.example.administrator.wanandroid.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by： wls
 * Created in： 2018/3/20
 * Describption：
 */
class SharedPref {

    private val FILE_NAME = "share_data"
    var sharedpre :SharedPreferences ?= null
    var editor : SharedPreferences.Editor ?= null
    constructor(context: Context){
        sharedpre = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE)
        editor = sharedpre!!.edit()
    }



    fun putString(key:String,value:String){
        editor!!.putString(key,value)
        editor!!.commit()
    }

    fun getString(key:String,value:String):String{
        return sharedpre!!.getString(key,value)
    }

    fun putInteger(key:String,value:Int){
        editor!!.putInt(key,value)
        editor!!.commit()
    }

    fun getInteger(key:String,value:Int):Int{
        return sharedpre!!.getInt(key,value)
    }

    fun putBoolean(key:String,bool:Boolean){
        editor!!.putBoolean(key,bool)
        editor!!.commit()
    }

    fun getBoolean(key:String,bool: Boolean):Boolean{
        return sharedpre!!.getBoolean(key,bool)
    }

    //移除已经存在的值
    fun remove(key:String){
        editor!!.remove(key)
        editor!!.commit()

    }
    //清除所有数据
    fun clear(){
        editor!!.clear()
        editor!!.commit()
    }

    //查询key是否存在
    fun check(key:String):Boolean{
        return sharedpre!!.contains(key)
    }

}