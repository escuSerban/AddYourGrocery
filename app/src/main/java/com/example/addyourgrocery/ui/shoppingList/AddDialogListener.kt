package com.example.addyourgrocery.ui.shoppingList

import com.example.addyourgrocery.data.db.entities.ShoppingItem


interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}