package com.fiqsky.githubuserapp.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import kotlinx.android.synthetic.main.item_following.view.*

class FollowingAdapter(private val list: MutableList<User>? = mutableListOf()) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_following, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User?) {
            Log.d(TAG, "bind: ")
            itemView.img_following.setImageResource(user?.avatar ?: 0)
            itemView.txt_following.text = user?.name
        }

    }
}