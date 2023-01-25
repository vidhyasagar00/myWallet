package com.example.myWallet.ui.currencySelection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myWallet.R
import com.example.myWallet.adapters.CurrencySelectionAdapter
import com.example.myWallet.adapters.ItemClickListener
import com.example.myWallet.databinding.FragmentCurrencySelectionBinding
import com.example.myWallet.extras.CurrencyList
import com.example.myWallet.utils.Constants
import com.example.myWallet.utils.MyPreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrencySelectionFragment : Fragment() {

    lateinit var binding: FragmentCurrencySelectionBinding
    private var currency: String? = null
    private var list = CurrencyList.list

    @Inject
    lateinit var preference: MyPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencySelectionBinding.inflate(inflater, container, false)

        binding.apply {

            currencySelector.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = CurrencySelectionAdapter(list, object : ItemClickListener {
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onSelect(position: Int) {
                        list.forEach { it.isAdded = false }
                        list[position].isAdded = true
                        adapter?.notifyDataSetChanged()
                        currency = list[position].symbol
                    }
                })
            }

            btnNext.setOnClickListener {
                if (currency != null) {
                    preference.set(Constants.CURRENCY_SYMBOL, currency!!)
                    preference.set(Constants.IS_USER_LOGIN, true)
                    findNavController().navigate(R.id.welcomeToHomeFragment)
                } else {
                    Toast.makeText(requireContext(), "please select currency type", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }
}