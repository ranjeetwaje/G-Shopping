package com.ranjeetwaje.g_shopping.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GroceryListViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GrocceryListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GrocceryListViewModel(application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}