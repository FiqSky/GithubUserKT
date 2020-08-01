package com.fiqsky.githubuserapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()
    private val titles = mutableListOf<String>()

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]

    //public method untuk menambahkan fragment dan title tab ke dalam list
    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
        notifyDataSetChanged()
    }
}