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

//    private var arrayList : ArrayList<User>? = arrayListOf()
    private var arrayList : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_following, container, false)
        arguments.let {
            arrayList = it?.getString(LIST_PARAMS).toString()
        }

//        view.rv_following.layoutManager = LinearLayoutManager(requireContext())
//        view.rv_following.adapter =
//            FollowingAdapter(arrayList)

        Log.d(TAG, "onCreateView: "+view)
        return view
    }

    companion object {
        //Membuat instance baru untuk class FollowingFragment. Passing data arraylist user.
        fun newInstance(arrayList: String): FollowingFragment {
            return FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(LIST_PARAMS,arrayList)
                    Log.d(TAG, "newInstance: "+arrayList)
                }
            }
        }
        const val LIST_PARAMS = "params_list"
    }
}