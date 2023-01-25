package com.example.myWallet.ui.addBudget

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myWallet.R
import com.example.myWallet.databinding.FragmentAddBudgetBinding
import com.example.myWallet.models.Category
import com.example.myWallet.utils.Constants
import com.example.myWallet.utils.navigateTo
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AddBudgetFragment : Fragment() {

    private val model: AddBudgetViewModel by viewModels()

    private lateinit var binding: FragmentAddBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBudgetBinding.inflate(inflater, container, false).apply {
            viewModel = model
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>(Constants.EXTRAS)?.observe(
            viewLifecycleOwner
        ) {
            if (it.getBoolean("isButtonRight")) {
                Toast.makeText(requireContext(), "right + data: ${it.getInt("id")}", Toast.LENGTH_SHORT).show()
            } else {
                model.setSelectedAccount(it.getInt("id"))
            }
        }

        lifecycleScope.launchWhenStarted {
            model.account.collectLatest {
                Log.e("tag", "title: ${it?.title}")
            }


            model.action.collectLatest {
                when (it) {
                    Category.INCOME -> changeCheckState(Category.INCOME)

                    Category.EXPENSE -> changeCheckState(Category.EXPENSE)

                    Category.TRANSFER -> changeCheckState(Category.TRANSFER)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            model.clicks.collectLatest {
                when (it) {
                    AddBudgetViewModel.SAVE,
                    AddBudgetViewModel.CANCEL -> findNavController().popBackStack()
                    else -> {}
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            model.clicks.collectLatest {
                when (it) {
                    AddBudgetViewModel.TO_ACCOUNT -> {
                        val action = AddBudgetFragmentDirections.openBottomSheet(it, true)
                        navigateTo(action)
                    }
                    else -> {
                        val action = AddBudgetFragmentDirections.openBottomSheet(it, false)
                        navigateTo(action)
                    }
                }
            }
        }
    }


    private fun changeCheckState(str: String) {
        binding.itmIncome.also { setNormalText(it) }
        binding.itmExpense.also { setNormalText(it) }
        binding.itmTransfer.also { setNormalText(it) }

        when (str) {
            Category.INCOME -> binding.itmIncome.also {
                setHighLightText(it)
                setAccountOrCategory(Category.INCOME)
            }
            Category.EXPENSE -> binding.itmExpense.also {
                setHighLightText(it)
                setAccountOrCategory(Category.EXPENSE)
            }
            Category.TRANSFER -> binding.itmTransfer.also {
                setHighLightText(it)
                setAccountOrCategory(Category.TRANSFER)
            }
        }
    }

    private fun setAccountOrCategory(str: String) {
        when (str) {
            Category.INCOME -> binding.apply {
                imgTypeRight.setBackgroundResource(R.drawable.category)
                txtTypeRight.text = "Category"
            }
            Category.EXPENSE -> binding.apply {
                imgTypeRight.setBackgroundResource(R.drawable.category)
                txtTypeRight.text = "Category"
            }
            Category.TRANSFER -> binding.apply {
                imgTypeRight.setBackgroundResource(R.drawable.wallet_1)
                txtTypeRight.text = "Account"
            }
        }
    }

    private fun setNormalText(it: TextView) {
        it.setTypeface(null, Typeface.NORMAL)
        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.midGray))
    }

    private fun setHighLightText(it: TextView) {
        it.setTypeface(null, Typeface.BOLD)
        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }
}