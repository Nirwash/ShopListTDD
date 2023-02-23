package com.nirwashh.android.shoplisttdd.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import javax.inject.Inject
import com.nirwashh.android.shoplisttdd.adapters.ShoppingItemAdapter.*
import com.nirwashh.android.shoplisttdd.data.local.ShoppingItem
import com.nirwashh.android.shoplisttdd.databinding.ItemShoppingBinding

class ShoppingItemAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ShoppingItemViewHolder>() {

    inner class ShoppingItemViewHolder(val binding: ItemShoppingBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<ShoppingItem>() {
        override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var shoppingItems: List<ShoppingItem>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        val binding = ItemShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingItemViewHolder(binding)
    }

    override fun getItemCount() = shoppingItems.size

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val shoppingItem = shoppingItems[position]
        holder.binding.apply {
            glide.load(shoppingItem.imageUrl).into(ivShoppingImage)
            tvName.text = shoppingItem.name
            tvShoppingItemAmount.text = "${shoppingItem.amount}x"
            tvShoppingItemPrice.text = "${shoppingItem.price}$"
        }
    }
}