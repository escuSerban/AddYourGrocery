package com.example.addyourgrocery.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.addyourgrocery.data.db.entities.ShoppingItem

/*
Dao interface required to access our Room database.
 */
@Dao
interface ShoppingDao {
    // used to insert and update items
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    // delete function that can be executed asynchronously
    @Delete
    suspend fun delete(item: ShoppingItem)

    //  returns the live data of our grocery list according to our "shopping_items" table
    @Query("select * from shopping_items")
    fun getAllShoppingItems(): LiveData<List<ShoppingItem>>
}