package com.example.myWallet.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myWallet.R
import com.example.myWallet.databinding.ItemAccountBinding
import com.example.myWallet.extras.MyList
import com.example.myWallet.models.Account

class BottomSheetAccountAdapter : RecyclerView.Adapter<BottomSheetAccountAdapter.ViewHolder>() {

    lateinit var list: List<Account>
    private var listener: ItemClickListener? = null

    fun setAccount(list: List<Account>) {
        this.list = list
    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemAccountBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position], listener)
    }

    override fun getItemCount() = list.size

    class ViewHolder(private val binding: ItemAccountBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(account: Account, listener: ItemClickListener?) {
            binding.imgAccount.setImageResource(MyList.account[account.icon])
            binding.txtAccount.text = account.title
            binding.txtAmount.text = account.initialAmount

            if (account.initialAmount.toDouble() <= 0) {
                binding.txtAmount.setTextColor(ContextCompat.getColor(binding.root.context, R.color.darkPrimary))
            } else {
                binding.txtAmount.setTextColor(ContextCompat.getColor(binding.root.context, R.color.teal_700))
            }

            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) listener?.onSelect(adapterPosition)
            }
        }
    }
}
