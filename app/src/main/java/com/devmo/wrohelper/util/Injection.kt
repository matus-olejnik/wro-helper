package com.devmo.wrohelper.util

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.devmo.wrohelper.db.AppRoomDatabase
import com.devmo.wrohelper.db.DataRepository
import com.devmo.wrohelper.db.LocalCache

/**
 * Class that handles object creation.
 * Like this, objects can be passed as parameters in the constructors and then replaced for
 * testing, where needed.
 */
object Injection {

    private fun provideCache(context: Context): LocalCache {
        val database = AppRoomDatabase.getInstance(context)
        return LocalCache(database.appDao())
    }

    private fun provideDataRepository(context: Context): DataRepository {
        return DataRepository.getInstance(provideCache(context))
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideDataRepository(context))
    }
}