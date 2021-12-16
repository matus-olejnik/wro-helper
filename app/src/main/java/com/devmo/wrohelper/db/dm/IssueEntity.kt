package com.devmo.wrohelper.db.dm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issue")
data class IssueEntity(
    @PrimaryKey val id: String,
    val message: String?,
    val solution: String?,
)