package com.fiqsky.githubuserapp.activity

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataAvatar: TypedArray
    private var users = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        users.addAll(getListUser())
        showRecyclerList()

    }

    private fun getListUser(): Collection<User> {
        dataName = resources.getStringArray(R.array.name)
        dataUsername = resources.getStringArray(R.array.username)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollowers = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataAvatar = resources.obtainTypedArray(R.array.avatar)

        val listUser = ArrayList<User>()
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
        return listUser
    }

    private fun showRecyclerList() {
        rv_main.layoutManager = LinearLayoutManager(this)
        val userAdapter = UserAdapter(users)
        rv_main.adapter = userAdapter

        userAdapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Toast.makeText(this@MainActivity, "Kamu memilih ${data.name}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, InfoActivity::class.java)
                intent.putExtra(InfoActivity.EXTRA_USER, data)
                startActivity(intent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}