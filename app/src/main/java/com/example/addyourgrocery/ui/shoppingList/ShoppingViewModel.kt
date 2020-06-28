package com.example.addyourgrocery.ui.shoppingList

import androidx.lifecycle.ViewModel
import com.example.addyourgrocery.data.db.entities.ShoppingItem
import com.example.addyourgrocery.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
We design our Model to use a Coroutine to execute methods, defined on @ShoppingRepository,
on the Main Thread(Room safety)
 */
class ShoppingViewModel(
    private val repository: ShoppingRepository

) : ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}