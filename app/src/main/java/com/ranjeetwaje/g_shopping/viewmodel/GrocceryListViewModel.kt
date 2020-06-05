package com.ranjeetwaje.g_shopping.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranjeetwaje.g_shopping.database.GroceryItem
import com.ranjeetwaje.g_shopping.database.GroceryItemDao
import com.ranjeetwaje.g_shopping.network.GroceryApi
import com.ranjeetwaje.g_shopping.network.GroceryData
import kotlinx.coroutines.*
import java.lang.Exception

class GrocceryListViewModel(val database: GroceryItemDao, application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _item = MutableLiveData<GroceryData>()
    val item : LiveData<GroceryData>
        get() = _item

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        initializeItemList()
    }

    private fun initializeItemList() {
        coroutineScope.launch {
//            item.value = getItemsFromDataBase()
            getGroceryItems()
        }
    }

    private suspend fun getItemsFromDataBase(): List<GroceryItem>? {
        return withContext(Dispatchers.IO) {
            var item = database.getAllItems()
            item
        }
    }

    private fun getGroceryItems() {
        coroutineScope.launch {
            var getGroceryItemsDeferred = GroceryApi.retrofitService.getGroceryItemsAsync()
            try {
                var listResult = getGroceryItemsDeferred.await()
                _response.value =
                    "Success: ${listResult.size} Mars properties retrieved"
                if (listResult.isNotEmpty()) {
                    _item.value = listResult[0]
                }
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

}