package com.fiqsky.githubuserapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
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

//        val list = getListUser()

        //Inisialisasi adapter untuk view pager
//        val adapter = SectionAdapter(supportFragmentManager)

        //Memanggil method addFragment() untuk menambahkan fragment dan title
//        adapter.addFragment(FollowingFragment.newInstance("holla"), "Follower")
//        adapter.addFragment(FollowingFragment.newInstance("ahoy"), "Following")

        //set adapter ke dalam view pager
//        view_pager.adapter = adapter
        //Jangan lupa untuk menghubungkan antara TabLayout dan ViewPager, dengan method setupWithViewPager()
//        tabs.setupWithViewPager(view_pager)

//        val user = intent.getParcelableExtra<User>(
//            EXTRA_USER
//        )
//
//        if (user != null) {
//            Picasso.get()
//                .load(user.avatar)
//                .placeholder(R.drawable.placeholder)
//                .into(img_avatar)
//            txt_name.text = user.name
//            txt_location.text = user.location
//            txt_work.text = user.company
//            txt_repo.text = user.repository
//            txt_followers.text = user.followers
//            txt_following.text = user.following
//        }
//    }
//
//    private fun getListUser(): ArrayList<User> {
//        val dataName = resources.getStringArray(R.array.name)
//        val dataUsername = resources.getStringArray(R.array.username)
//        val dataLocation = resources.getStringArray(R.array.location)
//        val dataRepository = resources.getStringArray(R.array.repository)
//        val dataCompany = resources.getStringArray(R.array.company)
//        val dataFollowers = resources.getStringArray(R.array.followers)
//        val dataFollowing = resources.getStringArray(R.array.following)
//        val dataAvatar = resources.obtainTypedArray(R.array.avatar)
//
//        val listUser = ArrayList<User>()
//        for (position in dataName.indices) {
//            val user = User(
//                dataAvatar.getResourceId(position, -1),
//                dataName[position],
//                dataUsername[position],
//                dataLocation[position],
//                dataRepository[position],
//                dataCompany[position],
//                dataFollowers[position],
//                dataFollowing[position]
//            )
//            users.add(user)
//        }
//        return listUser
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

    private fun initDetailUser(user: User?){
        txt_repo.text = user?.publicRepos.toString()
        txt_followers.text = user?.totalFollowers.toString()
        txt_followings.text = user?.totalFollowing.toString()
        txt_name.text = user?.name
        txt_location.text = user?.location
        txt_work.text = user?.company
        txt_link.text = user?.blog
        Picasso.get()
            .load(user?.avatarUrl)
            .placeholder(R.drawable.placeholder)
            .into(img_avatar)
        Glide.with(this@InfoActivity)
            .load(user?.avatarUrl)
            .placeholder(R.drawable.placeholder)
            .into(img_avatar)
    }
}