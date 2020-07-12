package com.fiqsky.githubuserapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val user = intent.getParcelableExtra<User>(
            EXTRA_USER
        )

        if (user != null) {
            Picasso.get()
                .load(user.avatar)
                .placeholder(R.drawable.placeholder)
                .into(img_avatar)
            txt_name.text = user.name
            txt_username.text = user.username
            txt_location.text = user.location
            txt_work.text = user.company
            txt_repo.text = user.repository
            txt_followers.text = user.followers
            txt_following.text = user.following
        }
    }
}