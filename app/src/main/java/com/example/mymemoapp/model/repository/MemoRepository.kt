package com.example.mymemoapp.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.mymemoapp.model.dao.MemoDao
import com.example.mymemoapp.model.entity.Memo

class MemoRepository(private val memoDao: MemoDao) {

    val allMemos: LiveData<List<Memo>> = memoDao.getAll()

    @WorkerThread
    suspend fun getMemo(id : Int): Memo = memoDao.getMemo(id)

    @WorkerThread
    suspend fun insertMemo(memo : Memo) = memoDao.insertMemo(memo)

    suspend fun deleteAll() = memoDao.deleteAll()

    suspend fun updateMemo(memo: Memo) = memoDao.updateMemo(memo)

    suspend fun deleteMemo(id : Int) = memoDao.deleteMemo(id)

    suspend fun setFavorite(id: Int, flag: Boolean) = memoDao.setFavorite(id, flag)
}