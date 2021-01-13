package com.example.mymemoapp.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymemoapp.R
import com.example.mymemoapp.view.adapter.MemoListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = recyclerview
        val adapter = MemoListAdapter()
        adapter.listener = this
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        memoViewModel.allMemo.observe(owner = this) { memos ->
            memos.let { adapter.submitList(it)}
        }

        btn_add.setOnClickListener {
            val intent = Intent(this, InputMemoActivity::class.java)
            startActivity(intent)
        }
    }
}
