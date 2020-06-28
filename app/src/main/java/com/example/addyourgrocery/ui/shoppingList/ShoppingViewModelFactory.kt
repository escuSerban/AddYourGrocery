package com.example.addyourgrocery.ui.shoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.addyourgrocery.data.repositories.ShoppingRepository

/*
This class allows us to pass in the repository constructor of our @ShoppingViewModel
 */
@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(
    private val repository: ShoppingRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}