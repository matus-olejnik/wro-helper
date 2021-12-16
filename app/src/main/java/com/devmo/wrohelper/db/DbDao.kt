package com.devmo.wrohelper.db

import androidx.room.Dao
import androidx.room.Query
import com.devmo.wrohelper.db.dm.IssueEntity


@Dao
interface DbDao {

    @Query("SELECT id FROM issue")
    suspend fun getIssuesIds(): List<String>

    @Query("SELECT * FROM issue where id = :id")
    suspend fun getIssuesById(id: String): IssueEntity
}