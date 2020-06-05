package com.ranjeetwaje.g_shopping.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranjeetwaje.g_shopping.database.GroceryItem
import com.ranjeetwaje.g_shopping.database.GroceryItemDao
import com.ranjeetwaje.g_shopping.network.GroceryApi
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class GrocceryListViewModel(val database: GroceryItemDao, application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var item = MutableLiveData<List<GroceryItem>?>()

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
            item.value = getItemsFromDataBase()
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
            var getGroceryItemsDeferred = GroceryApi.retrofitService.getGroceryItems()
            try {
                var listResult = getGroceryItemsDeferred.await()
                _response.value =
                    "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

}