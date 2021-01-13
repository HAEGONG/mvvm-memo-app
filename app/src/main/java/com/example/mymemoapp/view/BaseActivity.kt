package com.example.mymemoapp.view

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mymemoapp.MemoApplication
import com.example.mymemoapp.viewmodel.MemoViewModel
import com.example.mymemoapp.viewmodel.MemoViewModelFactory

open class BaseActivity : AppCompatActivity(), BaseCallInterface {

    val memoViewModel: MemoViewModel by viewModels {
        MemoViewModelFactory((application as MemoApplication).memoRepository)
    }

    override fun getViewModel(): MemoViewModel {
        return memoViewModel
    }
}