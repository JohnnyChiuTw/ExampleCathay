package com.sample.githubexample.presenter

import android.content.Context
import android.view.View
import com.sample.githubexample.model.UserModel

interface MainContract {
    interface View {
        fun setViews(result : String)
    }


    interface Presenter {
        fun doGetList(context: Context)
    }


}