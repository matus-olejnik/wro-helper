package com.devmo.wrohelper.db

import com.devmo.wrohelper.db.dm.IssueEntity

class LocalCache(private val dbDao: DbDao) {

    suspend fun getIssuesIds(): List<String> {
        return dbDao.getIssuesIds()
    }

    suspend fun getIssuesById(id: String): IssueEntity {
        return dbDao.getIssuesById(id)
    }
}