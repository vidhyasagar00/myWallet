package com.example.myWallet.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class HomeScreenVPAdapter(
    private var fragmentList: List<Fragment>,
    fManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fManager, lifecycle) {

    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment = fragmentList[position]
}