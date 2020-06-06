package com.ranjeetwaje.g_shopping.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ranjeetwaje.g_shopping.database.GroceryDatabase
import com.ranjeetwaje.g_shopping.database.GroceryItem
import com.ranjeetwaje.g_shopping.database.asDomainModel
import com.ranjeetwaje.g_shopping.network.GroceryApi
import com.ranjeetwaje.g_shopping.network.GroceryData
import com.ranjeetwaje.g_shopping.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GroceryItemRepository (private val database: GroceryDatabase) {

    val list: LiveData<List<GroceryData>> = Transformations.map(database.groceryItemDao.getAllItems()) {
        it.asDomainModel()
    }

//    val list: LiveData<List<GroceryData>> = database.groceryItemDao.getAllItems()

    suspend fun refreshGroceryList() {
        withContext(Dispatchers.IO) {
            val list = GroceryApi.retrofitService.getGroceryItemsAsync().await()
            database.groceryItemDao.insert(*list.asDatabaseModel())
        }
    }

}
