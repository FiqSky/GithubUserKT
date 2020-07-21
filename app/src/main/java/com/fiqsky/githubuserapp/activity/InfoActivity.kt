package com.fiqsky.githubuserapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fiqsky.githubuserapp.FollowingFragment
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.SectionAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.android.synthetic.main.desc_user.*
import kotlinx.android.synthetic.main.info_user.*
import java.util.ArrayList

class InfoActivity : AppCompatActivity() {

    private var users = arrayListOf<User>()
    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

//        val list = getListUser()

        //Inisialisasi adapter untuk view pager
        val adapter = SectionAdapter(supportFragmentManager)

        //Memanggil method addFragment() untuk menambahkan fragment dan title
        adapter.addFragment(FollowingFragment.newInstance("holla"), "Follower")
        adapter.addFragment(FollowingFragment.newInstance("ahoy"), "Following")

        //set adapter ke dalam view pager
        view_pager.adapter = adapter
        //Jangan lupa untuk menghubungkan antara TabLayout dan ViewPager, dengan method setupWithViewPager()
        tabs.setupWithViewPager(view_pager)

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
}