package com.fiqsky.githubuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiqsky.githubuserapp.api.ApiClient
import com.fiqsky.githubuserapp.utils.SearchResponse
import com.fiqsky.githubuserapp.utils.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _searchResults: MutableLiveData<List<User>> = MutableLiveData()
    val searchResults: LiveData<List<User>> = _searchResults

    fun searchUser(query: String) {
        val call = ApiClient.service.getSearchResult(query)
        call.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                Log.d("message", "onResponse: " + response.body())
                if (response.isSuccessful) {
                    val list = response.body()?.items
                    _searchResults.postValue(list)
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _searchResults.postValue(emptyList())
                Log.d("message", "onFailure: " + t.message)
            }
        })
    }
}