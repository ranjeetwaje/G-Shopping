package com.ranjeetwaje.g_shopping.network

import com.ranjeetwaje.g_shopping.database.GroceryItem
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkContainer(val groceryItems: List<GroceryItemList>)

@JsonClass(generateAdapter = true)
data class GroceryItemList(
    val Id: Int,
    val name: String,
    val category: String,
    val type: String
)

fun NetworkContainer.asDomainModel(): List<GroceryData> {
    return groceryItems.map {
        GroceryData(
            Id = it.Id,
            category = it.category,
            name = it.name,
            type = it.type)
    }
}

fun NetworkContainer.asDatabaseModel(): Array<GroceryItem> {
    return groceryItems.map {
        GroceryItem(
            itemId = it.Id,
            itemCategory = it.category,
            itemName = it.name,
            type = it.type
        )}.toTypedArray()
}