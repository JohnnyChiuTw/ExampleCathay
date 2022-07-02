package com.sample.githubexample.presenter

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class DetailPresenter(val view : DetailContract.View) : DetailContract.Presenter {
    override fun doGetUser(context: Context, name : String) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.github.com/users/"+name
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                view.setViews(response)

            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "請檢查網路", Toast.LENGTH_LONG).show()
                view.noData()

            })

        queue.add(stringRequest)
    }
}