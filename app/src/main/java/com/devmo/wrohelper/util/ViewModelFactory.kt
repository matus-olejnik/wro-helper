package com.devmo.wrohelper.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devmo.wrohelper.db.DataRepository
import com.devmo.wrohelper.screen.main.MainViewModel

/**
 * Factory for ViewModels
 */
class ViewModelFactory(private val repository: DataRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}