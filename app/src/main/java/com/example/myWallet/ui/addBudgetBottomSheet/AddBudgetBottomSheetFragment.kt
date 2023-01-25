package com.example.myWallet.ui.addBudgetBottomSheet

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myWallet.R
import com.example.myWallet.adapters.BottomSheetAccountAdapter
import com.example.myWallet.adapters.BottomSheetCategoryAdapter
import com.example.myWallet.adapters.ItemClickListener
import com.example.myWallet.databinding.FragmentAddBudgetBottomSheetBinding
import com.example.myWallet.ui.addBudget.AddBudgetViewModel
import com.example.myWallet.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class AddBudgetBottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddBudgetBottomSheetBinding
    val viewModel: AddBudgetBottomSheetViewModel by viewModels()

    private val args: AddBudgetBottomSheetFragmentArgs by navArgs()

    private var accAdapter = BottomSheetAccountAdapter()
    private var catAdapter = BottomSheetCategoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBudgetBottomSheetBinding.inflate(inflater, container, false).apply {
            categoryRecycler.isNestedScrollingEnabled = false //this line is added to scroll the bottomSheet + recyclerview
            title = if (args.type == AddBudgetViewModel.ACCOUNT) "Account" else "Category"
            model = viewModel.also {
                it.getCategory()
                it.getAccount()
            }
            lifecycleOwner = viewLifecycleOwner
        }

        when (args.type) {
            AddBudgetViewModel.ACCOUNT,
            AddBudgetViewModel.TO_ACCOUNT -> initAccountRecycler()

            else -> initCategoryRecycler()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.categoryList.collectLatest {
                catAdapter.setCategory(it.filter { f -> args.type!!.contains(f.type, true) })
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.accountList.collectLatest {
                accAdapter.setAccount(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.clicks.collectLatest {
                when (it) {
                    AddBudgetBottomSheetViewModel.ADD_CLICKED -> {
                        launch(Dispatchers.Main) {
                            when (args.type) {
                                AddBudgetViewModel.ACCOUNT -> findNavController().navigate(R.id.openAccountDialog)
                                else -> {
                                    val action = AddBudgetBottomSheetFragmentDirections.openCategoryDialog(args.type)
                                    findNavController().navigate(action)
                                }
                            }
                        }
                    }
                }
            }
        }

        listener()
        return binding.root
    }

    private fun listener() {
        binding.apply {
            accAdapter.setListener(object : ItemClickListener {
                override fun onSelect(position: Int) {
                    when {
                        args.isButtonRight -> {
                            setAccountDetails(position, true)
                        }
                        else -> {
                            setAccountDetails(position)
                        }
                    }
                }
            })
            catAdapter.setListener(object : ItemClickListener {
                override fun onSelect(position: Int) {
                    setCategoryDetails(position)
                }
            })
        }
    }

    private fun setAccountDetails(position: Int, isButtonRight: Boolean = false) {
        addSelectedToBudget(viewModel.accountList.value[position].id!!, isButtonRight)
    }

    private fun setCategoryDetails(position: Int) {
        addSelectedToBudget(viewModel.categoryList.value[position].id!!, true)
    }

    private fun addSelectedToBudget(id: Int, isButtonRight: Boolean = false) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            Constants.EXTRAS,
            bundleOf(
                "isButtonRight" to isButtonRight,
                "id" to id
            )
        )
        findNavController().popBackStack()
    }

    private fun initAccountRecycler() {
        binding.categoryRecycler.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = accAdapter
        }
    }

    private fun initCategoryRecycler() {
        binding.categoryRecycler.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 3)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    super.getItemOffsets(outRect, view, parent, state)
                    val position: Int = parent.getChildAdapterPosition(view) // item position

                    val spanCount = 3
                    val spacing = 75 //spacing between views in grid

                    if (position >= 0) {
                        val column = position % spanCount // item column
                        outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                        outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                        if (position < spanCount) { // top edge
                            outRect.top = spacing
                        }
                        outRect.bottom = spacing // item bottom
                    } else {
                        outRect.left = 0
                        outRect.right = 0
                        outRect.top = 0
                        outRect.bottom = 0
                    }
                }
            })
            adapter = catAdapter
        }
    }
}