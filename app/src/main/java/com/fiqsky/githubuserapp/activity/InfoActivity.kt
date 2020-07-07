package com.fiqsky.githubuserapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val user = intent.getParcelableExtra<User>(
            EXTRA_MOVIE
        )

        if (user != null){
            img_avatar.setImageResource(user.avatar)
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