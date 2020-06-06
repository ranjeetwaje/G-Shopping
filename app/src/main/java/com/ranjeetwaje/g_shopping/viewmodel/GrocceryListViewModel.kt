package com.ranjeetwaje.g_shopping.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.ranjeetwaje.g_shopping.database.GroceryItemDao
import com.ranjeetwaje.g_shopping.database.getDatabase
import com.ranjeetwaje.g_shopping.network.GroceryData
import com.ranjeetwaje.g_shopping.repository.GroceryItemRepository
import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.IllegalArgumentException

class GrocceryListViewModel(application: Application): AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val repository = GroceryItemRepository(getDatabase(application))

    private var _items = MutableLiveData<List<GroceryData>>()
    val items : LiveData<List<GroceryData>>
        get() = _items

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    val itemList = repository.list


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        initializeItemList()
    }

    private fun initializeItemList() {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        coroutineScope.launch {
            try {
                repository.refreshGroceryList()
            } catch (e: Exception) {

            }
        }
    }

    class ViewModelFactory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GrocceryListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GrocceryListViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
