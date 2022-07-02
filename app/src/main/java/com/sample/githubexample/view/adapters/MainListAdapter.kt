package com.sample.githubexample.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.githubexample.R
import com.sample.githubexample.model.UserModel
import com.squareup.picasso.Picasso


class MainListAdapter: RecyclerView.Adapter<MainListAdapter.mViewHolder>() {

    var mData = listOf<UserModel>()
    private lateinit var myListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position : Int)
    }

    fun setOnClickListener(listener : onItemClickListner){
        myListener = listener
    }

    inner class mViewHolder(itemView: View, listener: onItemClickListner):RecyclerView.ViewHolder(itemView){

        //把layout檔的元件們拉進來，指派給當地變數
        val icon = itemView.findViewById<ImageView>(R.id.iv_image)
        val name = itemView.findViewById<TextView>(R.id.tv_name)
        val blue = itemView.findViewById<TextView>(R.id.tv_site_admin)
        val llblue = itemView.findViewById<LinearLayout>(R.id.ll_blue)

        init{
            itemView.setOnClickListener{
                myListener.onItemClick(adapterPosition)
            }
        }

        fun bind(userModel: UserModel){

            Picasso.get()
                .load(userModel.avatar_url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(icon)

            name.text = userModel.login

            if(userModel.site_admin == "false"){
                llblue.visibility = View.GONE
            } else {
                llblue.visibility = View.VISIBLE
                blue.text = "STAFF"
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):mViewHolder {

        //載入項目模板
        val inflater = LayoutInflater.from(parent.context)
        val itemLayout = inflater.inflate(R.layout.list_item, parent, false)
        return mViewHolder(itemLayout, myListener)

    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: mViewHolder, position: Int) {

        //呼叫上面的bind方法來綁定資料
        holder.bind(mData[position])

    }

    //更新資料用
    fun updateList(list:ArrayList<UserModel>){
        mData = list
    }
}