package com.example.addyourgrocery.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.addyourgrocery.R
import com.example.addyourgrocery.data.db.entities.ShoppingItem
import com.example.addyourgrocery.ui.shoppingList.ShoppingViewModel
import kotlinx.android.synthetic.main.shopping_item.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem = items[position]

        holder.itemView.textView_name.text = currentShoppingItem.name
        holder.itemView.textView_amount.text = "${currentShoppingItem.amount}"

        holder.itemView.imageView_delete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }

        holder.itemView.imageView_plus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }

        holder.itemView.imageView_minus.setOnClickListener {
            if (currentShoppingItem.amount > 0) {
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }
    }

    inner class ShoppingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}