package com.ranjeetwaje.g_shopping.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GroceryItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg groceryItemList: GroceryItem)

    @Update
    fun update(groceryItemList: GroceryItem)

    @Query("select * from grocery_item_list_table")
    fun getAllItems(): LiveData<List<GroceryItem>>

//    @Query("select item_name from grocery_item_list_table WHERE category_name = :category")
//    fun getItemsByCategory(category: String): LiveData<List<groceryItemNameTuple>>
//
//    @Query("select category_name from grocery_item_list_table")
//    fun getCategories(): LiveData<List<categoryNameTuple>>

    @Query("delete from grocery_item_list_table")
    fun clear()

}

//data class groceryItemNameTuple(
//    @ColumnInfo(name = "item_name") val itemName: String?
//)
//
//data class categoryNameTuple(
//    @ColumnInfo(name = "category_name") val category: String?
//)