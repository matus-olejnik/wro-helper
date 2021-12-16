package com.devmo.wrohelper.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devmo.wrohelper.db.dm.IssueEntity

@Database(
    entities = [IssueEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun appDao(): DbDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java, "wroh.db"
            )
                .createFromAsset("database/issue.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}