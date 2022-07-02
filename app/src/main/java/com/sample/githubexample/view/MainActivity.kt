package com.sample.githubexample.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sample.githubexample.R
import com.sample.githubexample.model.UserModel
import com.sample.githubexample.presenter.MainContract
import com.sample.githubexample.presenter.MainPresenter
import com.sample.githubexample.view.adapters.MainListAdapter
import org.json.JSONArray


class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var recyclerView: RecyclerView
    val mainListAdapter = MainListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainPresenter = MainPresenter(this)
            mainPresenter.doGetList(this)
        recyclerView = findViewById(R.id.rv_content)
    }

    override fun setViews(result : String) {

        val arrayList = arrayListOf<UserModel>()
        val data = JSONArray(result)

        for(i in 0..data.length()-1){
            val userData = Gson().fromJson(data[i].toString(), UserModel::class.java)
            arrayList.add(userData)
        }

        mainListAdapter.updateList(arrayList)     //傳入資料
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mainListAdapter
        mainListAdapter.setOnClickListener(object : MainListAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra("name", arrayList.get(position).login)
                startActivity(intent)
            }
        })

    }





}