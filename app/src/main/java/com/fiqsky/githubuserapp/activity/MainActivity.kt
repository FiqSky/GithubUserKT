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
import com.fiqsky.githubuserapp.viewmodel.SearchViewModel
import com.fiqsky.githubuserapp.User
import com.fiqsky.githubuserapp.adapter.FollowingAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener {

    private lateinit var adapter: FollowingAdapter
    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        actionBar?.setDisplayHomeAsUpEnabled(true)

        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            SearchViewModel::class.java)

        searchViewModel.searchResults.observe(this, Observer { list: List<User>? ->
            progress(false)
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

    private fun progress(isVisible: Boolean) {
        iv_search.visibility = GONE
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
            progress(true)
            searchViewModel.searchUser(query)
            return true
        }
        return false
    }
}