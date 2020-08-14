package com.fiqsky.githubuserapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiqsky.githubuserapp.FollowingFragment
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.SectionAdapter
import com.fiqsky.githubuserapp.api.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.desc_user.*
import kotlinx.android.synthetic.main.info_user.*
import java.util.ArrayList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "user"
    }

    private lateinit var adapter: SectionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
//        title = getString(R.id.detail)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)
        val userName = user?.userName ?: ""
        getDetail(userName)

        adapter = SectionAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        tabs.setupWithViewPager(view_pager)
    }
    private fun getFollowers(userName: String, title: String) {
        val call = ApiClient.service.getFollowers(userName)
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>
            ) {
                if (response.isSuccessful) {
                    val list = ArrayList<User>(response.body().orEmpty())
                    adapter.addFragment(FollowingFragment.newInstance(list), title)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

        })
    }

    private fun getFollowing(userName: String, title: String) {
        val call = ApiClient.service.getFollowing(userName)
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val list = ArrayList<User>(response.body().orEmpty())
                adapter.addFragment(FollowingFragment.newInstance(list), title)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }

        })

    }

    private fun getDetail(userName: String) {
        val call = ApiClient.service.getDetail(userName)
        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    initDetailUser(user)
                    getFollowing(userName, "${user?.totalFollowing} Following")
                    getFollowers(userName, "${user?.totalFollowers} Followers")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

            }
        })
    }

    @SuppressLint("SetText")
    private fun initDetailUser(user: User?){
        txt_repo.text = user?.publicRepos.toString()
        txt_followers.text = user?.totalFollowers.toString()
        txt_followings.text = user?.totalFollowing.toString()
        txt_name.text = user?.name
        if (user?.location != null){
            txt_location.text = user?.location
        }else{
            txt_location.text = "-"
        }
        if (user?.company != null) {
            txt_work.text = user.company
        } else {
            txt_work.text = "-"
        }
        if (user?.blog != null) {
            val url = user.blog
            txt_link.setOnClickListener {
                val intent = Intent (Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }.toString()
        } else{
            txt_link.text = "-"
        }
        Picasso.get()
            .load(user?.avatarUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.color.design_default_color_error)
            .into(img_avatar)
    }
}