package com.sample.githubexample.presenter

import android.content.Context

interface DetailContract {
    interface View {
        fun setViews(result : String)
        fun noData();
    }


    interface Presenter {
        fun doGetUser(context: Context, name : String)
    }

}