package com.example.mymemoapp.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mymemoapp.model.entity.Memo

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo ORDER BY favorite DESC, update_date DESC")
    fun getAll(): LiveData<List<Memo>>

    @Insert
    suspend fun insertMemo(memo: Memo)

    @Query("DELETE FROM memo")
    suspend fun deleteAll()

    @Query("SELECT * FROM memo WHERE id = :id LIMIT 1")
    suspend fun getMemo(id: Int) : Memo

    @Update
    suspend fun updateMemo(memo: Memo)

    @Query("DELETE FROM memo WHERE id = :id")
    suspend fun deleteMemo(id: Int)

    @Query("UPDATE memo SET favorite = :flag WHERE id = :id")
    suspend fun setFavorite(id: Int, flag: Boolean)
}