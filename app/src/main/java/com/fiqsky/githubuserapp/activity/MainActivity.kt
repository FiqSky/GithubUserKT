package com.fiqsky.githubuserapp.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiqsky.githubuserapp.R
import com.fiqsky.githubuserapp.SearchViewModel
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.FollowingAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {

    private lateinit var adapter: FollowingAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)

        searchViewModel.searchResults.observe(this, Observer { list: List<User>? ->
            showProgress(false)
            adapter.addAll(list)
        })

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

    private fun showProgress(isVisible: Boolean) {
        progress_bar.visibility = if (isVisible) {
            VISIBLE
        } else {
            GONE
        }
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

    override fun onEditorAction(textView: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val query = textView?.text.toString()
            showProgress(true)
            searchViewModel.searchUser(query)
            return true
        }
        return false
    }

    /*private fun searchUser(query: String) {
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
//                Snackbar.make(this,"Nope",Snackbar.LENGTH_LONG).show()
            }
        })
    }*/
}