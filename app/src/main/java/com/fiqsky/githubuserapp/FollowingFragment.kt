package com.fiqsky.githubuserapp

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fiqsky.githubuserapp.adapter.FollowingAdapter
import kotlinx.android.synthetic.main.fragment_following.view.*

class FollowingFragment : Fragment() {

    private var arrayList: ArrayList<User>? = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_following, container, false)
        arguments.let {
            arrayList = it?.getParcelableArrayList(LIST_PARAMS)
        }

        view.rv_following.layoutManager = LinearLayoutManager(requireContext())
        view.rv_following.adapter =
            FollowingAdapter(arrayList)

        return view
    }

    companion object {
        //Membuat instance baru untuk class FollowingFragment. Passing data arraylist user.
        fun newInstance(arrayList: ArrayList<User>): FollowingFragment {
            return FollowingFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(LIST_PARAMS, arrayList)
                }
            }
        }

        const val LIST_PARAMS = "params_list"
    }
}