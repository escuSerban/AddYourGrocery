package com.example.addyourgrocery.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.addyourgrocery.data.db.entities.ShoppingItem

/*
Here we have the Abstract Class that represents our actual Database.
 */
@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase: RoomDatabase() {

    // with this method we make sure Dao is accessible
    abstract fun getShoppingDao(): ShoppingDao

    // guarantees @ShoppingDatabase has one instance only
    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()

        // called every time we create an instance of ShoppingDatabase
        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: createDatabase(
                    context
                )
                    .also { instance = it }
        }

        // method used to instantiate our Database
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingDatabase::class.java, "ShoppingDB.db").build()
    }
}