package com.example.mymemoapp.view

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.example.mymemoapp.MemoApplication
import com.example.mymemoapp.R
import com.example.mymemoapp.model.entity.Memo
import com.example.mymemoapp.util.DateUtil
import com.example.mymemoapp.viewmodel.MemoViewModel
import com.example.mymemoapp.viewmodel.MemoViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_memo.*
import kotlinx.coroutines.launch

class MemoActivity : BaseActivity() {
    lateinit var mMemo: Memo

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        val id = intent.getIntExtra("id", -1)

        if (id == -1) {
            finish()
        }

        memoViewModel.viewModelScope.launch {
            mMemo = memoViewModel.getMemo(id)
            memo_title.editText?.setText(mMemo.title)
            memo_body.editText?.setText(mMemo.body)
        }

        button_edit.setOnClickListener {

            val title = memo_title.editText?.text.toString()
            val body = memo_body.editText?.text.toString()
            val date = DateUtil().getCurrentDate()

            if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(body) ) {
                val result = Memo(
                    id = mMemo.id,
                    title = title,
                    body = body,
                    insertDate = mMemo.insertDate,
                    updateDate = date)
                memoViewModel.updateMemo(result)

                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setMessage(resources.getString(R.string.title_body_empty_dialog_message))
                    .setPositiveButton(resources.getString(R.string.cancel)) { dialog, which ->
                    }
                    .show()
            }
        }

        app_bar.setOnMenuItemClickListener{menuItem ->
            when (menuItem.itemId) {
                R.id.delete -> {
                    memoViewModel.deleteMemo(mMemo.id)
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}