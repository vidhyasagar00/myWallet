package com.example.myWallet.ui.homeFragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel: ViewModel() {
    val currentFragment = MutableStateFlow<Fragment>(HomeFragment())
}