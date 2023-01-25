package com.example.myWallet.ui.tabAnalysisFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myWallet.databinding.FragmentTabAnalysisBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class TabAnalysisFragment : Fragment() {
    lateinit var binding: FragmentTabAnalysisBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabAnalysisBinding.inflate(inflater, container, false)

        val list = arrayListOf(
            PieEntry(10f, "one"),
            PieEntry(50f, "two"),
            PieEntry(102f, "three"),
            PieEntry(103f, "four"),
            PieEntry(70f, "five"),
        )

        val pieDataSet = PieDataSet(list, "")
        pieDataSet.apply {
            setColors(ColorTemplate.COLORFUL_COLORS, 255)
            valueTextSize = 10f
            valueTextColor = Color.BLACK
        }
        val pieData = PieData(pieDataSet)

        binding.pieChart.apply {
            data = pieData
            description.text = ""
            centerText = "demo"
            -(1000)
        }

        return binding.root
    }
}