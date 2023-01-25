package com.example.myWallet.ui.newCategoryPopup

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myWallet.R
import com.example.myWallet.databinding.FragmentNewCategoryPopupBinding

class NewCategoryPopupFragment : DialogFragment() {
    lateinit var binding: FragmentNewCategoryPopupBinding
    private val args: NewCategoryPopupFragmentArgs by navArgs()
    private val viewModel: NewCategoryPopupViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewCategoryPopupBinding.inflate(inflater, container, false).apply {
            model = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
//        args.type?.let { viewModel.setType(it) }


        return binding.root
    }

    private fun setNormalText(it: TextView) {
        it.setTypeface(null, Typeface.NORMAL)
        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.midGray))
    }

    private fun setHighLightText(it: TextView) {
        it.setTypeface(null, Typeface.BOLD)
        it.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
    }


    override fun onStart() {
        super.onStart()
        setWindowParams()
    }

    private fun setWindowParams() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }
}