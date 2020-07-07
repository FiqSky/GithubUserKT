package com.fiqsky.githubuserapp.activity

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.UserAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: UserAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var fab: FloatingActionButton
    private lateinit var dataAvatar: TypedArray
    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.lv_main)

        adapter = UserAdapter(this)

        listView.adapter = adapter

        initFab()
        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, users[position].name, Toast.LENGTH_SHORT).show()
            val infoActivity = Intent(this@MainActivity, InfoActivity::class.java)
            infoActivity.putExtra(InfoActivity.EXTRA_MOVIE, users[position])
            startActivity(infoActivity)
        }
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                dataAvatar.getResourceId(position, -1),
                dataName[position],
                dataUsername[position],
                dataLocation[position],
                dataRepository[position],
                dataCompany[position],
                dataFollowers[position],
                dataFollowing[position]
            )
            users.add(user)
        }
        adapter.users = users
    }

    private fun initFab() {
        fab = ActivityCompat.requireViewById(this, R.id.fab)
        val mode = AppCompatDelegate.getDefaultNightMode()
        initFab(mode)
    }

    private fun initFab(mode: Int) {
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                fab.setImageResource(R.drawable.ic_mode_night_no_black_24dp)
                fab.setOnClickListener { setNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                fab.setImageResource(R.drawable.ic_mode_night_yes_black_24dp)
                fab.setOnClickListener { setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM) }
            }
            else -> {
                fab.setImageResource(R.drawable.ic_mode_night_default_black_24dp)
                fab.setOnClickListener { setNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
            }
        }
    }

    private fun setNightMode(mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
        initFab(mode)
    }
}