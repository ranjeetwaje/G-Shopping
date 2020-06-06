package com.ranjeetwaje.g_shopping.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryItem::class], version = 1)
abstract class GroceryDatabase : RoomDatabase() {
    abstract val groceryItemDao: GroceryItemDao
}

private lateinit var INSTANCE: GroceryDatabase

fun getDatabase(context: Context): GroceryDatabase {
    synchronized(GroceryDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                GroceryDatabase::class.java,
                "grocery_database").build()
        }
    }
    return INSTANCE
}