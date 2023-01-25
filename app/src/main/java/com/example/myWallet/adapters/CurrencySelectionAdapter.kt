package com.example.myWallet.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myWallet.databinding.ItemCurrencyBinding
import com.example.myWallet.models.Currency

class CurrencySelectionAdapter(private val list: List<Currency>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<CurrencySelectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(currency: Currency, listener: ItemClickListener) {
            binding.apply {

                imgCheck.visibility = if(currency.isAdded) View.VISIBLE else View.INVISIBLE

                root.setOnClickListener {
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        listener.onSelect(adapterPosition)
                    }
                }
                txtCurrency.text = "${currency.name} - ${currency.country}"
            }
        }
    }
}