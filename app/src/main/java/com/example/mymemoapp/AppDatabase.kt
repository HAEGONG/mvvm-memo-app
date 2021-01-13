package com.example.mymemoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mymemoapp.model.dao.MemoDao
import com.example.mymemoapp.model.entity.Memo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Memo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object {
        private const val DB_NAME = "my-memo"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}