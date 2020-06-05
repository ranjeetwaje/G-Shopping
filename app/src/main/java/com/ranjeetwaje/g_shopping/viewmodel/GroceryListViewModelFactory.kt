package com.ranjeetwaje.g_shopping.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ranjeetwaje.g_shopping.database.GroceryItemDao
import java.lang.IllegalArgumentException

class GroceryListViewModelFactory(private val dataSource: GroceryItemDao,
                                  private val application: Application): ViewModelProvider.Factory {
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GrocceryListViewModel::class.java)) {
            return GrocceryListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}