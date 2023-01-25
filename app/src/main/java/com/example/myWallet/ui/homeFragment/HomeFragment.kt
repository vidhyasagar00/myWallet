package com.example.myWallet.ui.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.myWallet.R
import com.example.myWallet.adapters.HomeScreenVPAdapter
import com.example.myWallet.databinding.FragmentHomeBinding
import com.example.myWallet.ui.tabAnalysisFragment.TabAnalysisFragment
import com.example.myWallet.ui.tabBudgetFragment.TabBudgetFragment
import com.example.myWallet.ui.tabHomeFragment.TabHomeFragment
import com.example.myWallet.ui.tabProfileFragment.TabProfileFragment
import com.example.myWallet.utils.Constants
import com.example.myWallet.utils.MyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    lateinit var preferenceManager: MyPreferenceManager
    lateinit var binding: FragmentHomeBinding
    val menus = listOf(R.id.nav_home, R.id.nav_analysis, R.id.nav_budget, R.id.nav_profile)
    override fun onStart() {
        super.onStart()
        if (!preferenceManager.getBoolean(Constants.IS_USER_LOGIN)) {
            findNavController().navigate(R.id.HomeToWelcomeFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
//        replaceFragment(TabHomeFragment())


        val fragments = listOf(TabHomeFragment(), TabAnalysisFragment(), TabBudgetFragment(), TabProfileFragment())
        val myAdapter = HomeScreenVPAdapter(fragments, childFragmentManager, lifecycle)

        binding.apply {
            homeBtmNavView.also {
                it.menu[2].isEnabled = false
                it.setOnItemSelectedListener { menu -> moveToFragment(menu) }
                it.selectedItemId = menus[viewPager.currentItem]
            }

            viewPager.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                adapter = myAdapter
            }


        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewPager.registerOnPageChangeCallback(viewPagerPageChangeCallback)
        }
        binding.btnAdd.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.homeToBudgetFragment)
        }
    }

    private val viewPagerPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            if (position >= 2)
                binding.homeBtmNavView.menu[position + 1].isChecked = true
            else
                binding.homeBtmNavView.menu[position].isChecked = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPagerPageChangeCallback.let { binding.viewPager.unregisterOnPageChangeCallback(it) }
    }

    private fun moveToFragment(it: MenuItem): Boolean {
        when (it.itemId) {
            R.id.nav_home -> moveToFragment(0)

            R.id.nav_analysis -> moveToFragment(1)

            R.id.nav_budget -> moveToFragment(2)

            R.id.nav_profile -> moveToFragment(3)
        }
        return true
    }

    private fun moveToFragment(position: Int) {
        binding.viewPager.currentItem = position
    }

    /* private fun openFragmentWithId(it: MenuItem): Boolean {
         when (it.itemId) {
             R.id.nav_home -> replaceFragment(TabHomeFragment())

             R.id.nav_analysis -> replaceFragment(TabAnalysisFragment())

             R.id.nav_budget -> replaceFragment(TabBudgetFragment())

             R.id.nav_profile -> replaceFragment(TabProfileFragment())
         }
         return true
     }

     private fun replaceFragment(fragment: Fragment) {
         val manager: FragmentManager = parentFragmentManager
         val transaction = manager.beginTransaction()
         transaction.replace(R.id.homeScreenFrameLayout, fragment).commit()
    }*/
}