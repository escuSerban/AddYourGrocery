package com.example.addyourgrocery.data.repositories

import com.example.addyourgrocery.data.db.ShoppingDatabase
import com.example.addyourgrocery.data.db.entities.ShoppingItem

/*
We implement our Dao methods and then use this class on ViewModel
 */
class ShoppingRepository(
    private val db: ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}