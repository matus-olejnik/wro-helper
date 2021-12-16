package com.devmo.wrohelper.db

import android.util.Log
import com.devmo.wrohelper.db.dm.IssueEntity

class DataRepository private constructor(
    private val cache: LocalCache
) {

    companion object {
        const val TAG = "DataRepository"

        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(cache: LocalCache): DataRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataRepository(cache).also { INSTANCE = it }
            }
    }

    suspend fun getIssuesIds(): List<String> {

        val issuesIds = cache.getIssuesIds()
        Log.i(TAG, "Fetching issues ids $issuesIds")
        return issuesIds
    }

    suspend fun getIssuesById(id: String): IssueEntity {
        val issuesById = cache.getIssuesById(id)
        Log.i(TAG, "Getting issue $issuesById")
        return issuesById
    }
}