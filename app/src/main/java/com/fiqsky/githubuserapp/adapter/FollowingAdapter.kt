package com.fiqsky.githubuserapp.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import kotlinx.android.synthetic.main.item_user.view.*

class FollowingAdapter(

    private val list: MutableList<User>? = mutableListOf(),
    private val onClick: ((User) -> Unit)? = null
) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list?.get(position))
    }

    fun addAll(result: List<User>?) {
        if (result != null) {
            list?.clear()
            list?.addAll(result)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User?) {
            with(itemView) {
                Glide.with(context)
                    .load(user?.avatarUrl)
                    .placeholder(R.drawable.placeholder)
                    .error(android.R.color.darker_gray)
                    .into(itemView.iv_avatar)
                itemView.tv_name.text = user?.name
                itemView.tv_username.text = user?.userName
                itemView.setOnClickListener {
                    if (user != null) {
                        onClick?.invoke(user)
                    }
                }
            }
        }

    }
}