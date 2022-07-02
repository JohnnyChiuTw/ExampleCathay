package com.sample.githubexample.presenter

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainPresenter(val view : MainContract.View) : MainContract.Presenter{

    override fun doGetList(context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.github.com/users"
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                view.setViews(response)

            },
            Response.ErrorListener { error ->
                Toast.makeText(context, "請檢查網路", Toast.LENGTH_LONG).show()

            })

        queue.add(stringRequest)
    }
}