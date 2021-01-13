package com.example.mymemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mymemoapp.model.entity.Memo
import com.example.mymemoapp.model.repository.MemoRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MemoViewModel (private val repository: MemoRepository) : ViewModel() {
    val allMemo: LiveData<List<Memo>> = repository.allMemos

    suspend fun getMemo(id: Int): Memo = repository.getMemo(id)

    fun insertMemo(memo: Memo) = viewModelScope.launch {
        repository.insertMemo(memo)
    }

    fun updateMemo(memo: Memo) = viewModelScope.launch {
        repository.updateMemo(memo)
    }

    fun deleteMemo(id: Int) = viewModelScope.launch {
        repository.deleteMemo(id)
    }

    fun setFavorite(id: Int, flag: Boolean) = viewModelScope.launch {
        repository.setFavorite(id, flag)
    }

}

class MemoViewModelFactory(private val repository: MemoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MemoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}