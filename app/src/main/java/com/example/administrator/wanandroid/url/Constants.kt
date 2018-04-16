package com.example.administrator.wanandroid.url

/**
 * Created by： wls
 * Created in： 2018/3/14
 * Describption：
 */

interface Constants {

    interface Code{
        companion object {
            const val IS_LOGIN = "is_login"
            const val USERNAME = "username"
            const val PASSWORD = "password"
        }

    }
    interface URL {
        companion object {
            const val Host = "http://www.wanandroid.com/"
            //const：让其以public static final的字段呈现给调用者
            const val BANNER = "banner/json"

            const val SEARCH = "/hotkey/json"

            const val CLASSIFYTAB = "tree/json"

            const val LOGIN = "user/login"

            const val REGISTER = "user/register"


        }

    }
}