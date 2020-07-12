package com.fiqsky.githubuserapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import com.squareup.picasso.Picasso

class UserAdapter(private val listuser: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        fun bind(user: User){
            Picasso.get()
                .load(user.avatar)
                .into(ivAvatar)
            tvName.text = user.name
            tvUsername.text = user.username

            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(user) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listuser.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listuser[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}
