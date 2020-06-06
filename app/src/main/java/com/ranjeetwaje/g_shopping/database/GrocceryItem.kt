package com.ranjeetwaje.g_shopping.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ranjeetwaje.g_shopping.network.GroceryData

@Entity(tableName = "grocery_item_list_table")
data class GroceryItem constructor (
    @PrimaryKey
    val itemId: Int,

    @ColumnInfo(name = "item_name")
    val itemName: String,

    @ColumnInfo(name = "category_name")
    val itemCategory: String,

    val type: String
)

fun List<GroceryItem>.asDomainModel(): List<GroceryData> {
    return map {
        GroceryData(
            Id = it.itemId,
            name = it.itemName,
            category = it.itemCategory,
            type = it.type
        )
    }
}
