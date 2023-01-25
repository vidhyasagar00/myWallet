package com.example.myWallet.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myWallet.databinding.ItemCategoryBinding
import com.example.myWallet.extras.DefaultCategories
import com.example.myWallet.extras.MyList
import com.example.myWallet.models.Category

class BottomSheetCategoryAdapter : RecyclerView.Adapter<BottomSheetCategoryAdapter.ViewHolder>() {

    lateinit var list: List<Category>
    private var listener: ItemClickListener? = null

    fun setCategory(list: List<Category>) {
        this.list = list
    }

    fun setListener(listener: ItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list[position], listener)
    }

    override fun getItemCount() = list.size

    class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(account: Category, listener: ItemClickListener?) {
            binding.imgCategory.setImageResource(MyList.category[account.icon])
            binding.txtCategory.text = account.title

            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) listener?.onSelect(adapterPosition)
            }
        }
    }
}