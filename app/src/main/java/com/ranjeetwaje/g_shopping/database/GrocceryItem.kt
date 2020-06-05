package com.ranjeetwaje.g_shopping.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_item_list_table")
data class GroceryItem (
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    @ColumnInfo(name = "item_name")
    var itemName: String = "",

    @ColumnInfo(name = "category_name")
    var itemCategory: String = ""
)