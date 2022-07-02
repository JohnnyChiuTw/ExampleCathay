package com.sample.githubexample.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.sample.githubexample.R
import com.sample.githubexample.model.SingleUserModel
import com.sample.githubexample.presenter.DetailContract
import com.sample.githubexample.presenter.DetailPresenter
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(), DetailContract.View  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name:String = intent.getStringExtra("name").toString()
        val detailPresenter = DetailPresenter(this)
        detailPresenter.doGetUser(this, name)
    }

    override fun setViews(result: String) {
        Log.d("Detail", result)
        val userData = Gson().fromJson(result, SingleUserModel::class.java)
        var close = findViewById<ImageView>(R.id.iv_close)
        var head = findViewById<ImageView>(R.id.iv_detail_image)
        var name = findViewById<TextView>(R.id.tv_detail_name)
        var bio = findViewById<TextView>(R.id.iv_bio)
        var login = findViewById<TextView>(R.id.tv_detail_loginname)
        var llBlue = findViewById<LinearLayout>(R.id.ll_blue)
        var admin = findViewById<TextView>(R.id.tv_site_admin)
        var location = findViewById<TextView>(R.id.tv_location)
        var blog = findViewById<TextView>(R.id.tv_blog)

        close.setOnClickListener{
            finish()
        }

        Picasso.get()
            .load(userData.avatar_url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(head)

        bio.text = userData.bio
        if(userData.site_admin == "false"){
            llBlue.visibility = View.GONE
        } else {
            llBlue.visibility = View.VISIBLE
            admin.text = userData.site_admin
        }
        name.text = userData.name
        login.text = userData.login
        location.text = userData.location
        blog.text = userData.blog
        blog.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(userData.blog))
            startActivity(i)
        }

    }

    override fun noData() {
        finish()
    }
}