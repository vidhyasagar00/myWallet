package com.example.myWallet.ui.tabHomeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myWallet.databinding.FragmentTabHomeBinding
import com.example.myWallet.db.AccountsDao
import com.example.myWallet.models.Account
import com.example.myWallet.utils.Constants
import com.example.myWallet.utils.MyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class TabHomeFragment : Fragment() {

    @Inject
    lateinit var db: AccountsDao

    @Inject
    lateinit var preference: MyPreferenceManager

    val model: TabHomeViewModel by viewModels()
    lateinit var binding: FragmentTabHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabHomeBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            currency = preference.getString(Constants.CURRENCY_SYMBOL)

            viewModel = model.also {
                it.getAccountsList()

                lifecycleScope.launchWhenCreated {
                    it.spinner.collect { spinner ->
                        if (spinner != null) {
                            if (spinner.isNotEmpty())
                                launch(Dispatchers.Main) {
                                    delay(5000)
                                    parentView.visibility = View.VISIBLE
                                    cardShimmer.apply {
                                        stopShimmer()
                                        visibility = View.GONE
                                    }
                                }
                        } else {
                            launch(Dispatchers.Main) {
                                parentView.visibility = View.GONE
                                cardShimmer.apply {
                                    startShimmer()
                                    visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }

//            lifecycleScope.launch {
//                launch(Dispatchers.Main) {
//                    parentView.visibility = View.GONE
//                    cardShimmer.apply {
//                        startShimmer()
//                        visibility = View.VISIBLE
//                    }
//                }
//
//                delay(5000)
//
//                launch(Dispatchers.Main) {
//                    parentView.visibility = View.VISIBLE
//                    cardShimmer.apply {
//                        stopShimmer()
//                        visibility = View.GONE
//                    }
//                }
//            }
        }


        /*binding.setting.setOnClickListener {
            lifecycleScope.launch {
                model._accounts.value += withContext(lifecycleScope.coroutineContext) { getAccountsList().toMutableList() }
            }
        }*/

        return binding.root
    }

    private suspend fun getAccountsList(): List<Account> {
        return withContext(lifecycleScope.coroutineContext + Dispatchers.Default) { db.getAllAccounts() }.toList()
    }
}