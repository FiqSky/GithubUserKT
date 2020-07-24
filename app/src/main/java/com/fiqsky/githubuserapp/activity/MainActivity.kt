package com.fiqsky.githubuserapp.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.SearchResponse
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.FollowingAdapter
import com.fiqsky.githubuserapp.api.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {
/*
    private lateinit var dataName: Array<String>
    private lateinit var dataUsername: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollowers: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataAvatar: TypedArray
    private var users = arrayListOf<User>()
*/
    private lateinit var adapter: FollowingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*users.addAll(getListUser())
        showRecyclerList()*/

        initRecyclerView()
        edit_search.setOnEditorActionListener(this)

    }

    private fun initRecyclerView() {
        adapter = FollowingAdapter(onClick = { user: User ->
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra(InfoActivity.EXTRA_USER, user)
            startActivity(intent)
        })
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = adapter
    }

    /*private fun getListUser(): ArrayList<User> {
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
    }*/

    /*private fun showRecyclerList() {
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
    }*/

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

    override fun onEditorAction(textView: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val query = textView?.text.toString()
            searchUser(query)
            return true
        }
        return false
    }

    private fun searchUser(query: String) {
        //Menampilkan loading progressbar
        progress_bar.visibility = VISIBLE
        val call = ApiClient.service.getSearchResult(query)
        call.enqueue(object : Callback<SearchResponse> {
            //Responnya berhasil, Http code == 200
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val list = response.body()?.items
                    //tambah ke adapter
                    adapter.addAll(list)
                    //Menghilangkan progressbar
                    progress_bar.visibility = GONE
                }

            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                progress_bar.visibility = GONE
            }
        })
    }
}