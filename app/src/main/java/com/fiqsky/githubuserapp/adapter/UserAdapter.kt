package com.fiqsky.githubuserapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User

class UserAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = ArrayList<User>()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View? {
        var itemView = p1
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, p2, false)
        }
        val viewHolder = ViewHolder(itemView as View)

        val some = getItem(p0) as User
        viewHolder.bind(some)
        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val ivAvatar: ImageView = view.findViewById(R.id.iv_avatar)
        private val tvName: TextView = view.findViewById(R.id.tv_name)
        private val tvUsername: TextView = view.findViewById(R.id.tv_username)
        private val tvLocation: TextView = view.findViewById(R.id.tv_location)
        private val tvFollowers: TextView = view.findViewById(R.id.tv_wers)
        private val tvFollowing: TextView = view.findViewById(R.id.tv_wing)

        internal fun bind(user: User) {
            ivAvatar.setImageResource(user.avatar)
            tvName.text = user.name
            tvUsername.text = user.username
            tvLocation.text = user.location
            tvFollowers.text = user.followers
            tvFollowing.text = user.following
        }
    }

    override fun getItem(i: Int): Any {
        return users[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return users.size
    }
}
