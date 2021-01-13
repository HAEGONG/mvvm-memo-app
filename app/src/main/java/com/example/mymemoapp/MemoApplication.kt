package com.example.mymemoapp

import android.app.Application
import com.example.mymemoapp.model.repository.MemoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MemoApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val memoRepository by lazy { MemoRepository(database.memoDao()) }
}