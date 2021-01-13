package com.example.mymemoapp.view

import android.os.Bundle
import android.text.TextUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mymemoapp.MemoApplication
import com.example.mymemoapp.R
import com.example.mymemoapp.model.entity.Memo
import com.example.mymemoapp.util.DateUtil
import com.example.mymemoapp.viewmodel.MemoViewModel
import com.example.mymemoapp.viewmodel.MemoViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_input_memo.*
import java.util.*

class InputMemoActivity : BaseActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_memo)

        button_save.setOnClickListener {
            val title = edit_title.editText?.text.toString()
            val body = edit_body.editText?.text.toString()
            val date = DateUtil().getCurrentDate()

            if (!TextUtils.isEmpty(title) || !TextUtils.isEmpty(body) ) {
                val memo = Memo(
                    title = title,
                    body = body,
                    insertDate = date,
                    updateDate = date)
                memoViewModel.insertMemo(memo)
                finish()
            } else {
                MaterialAlertDialogBuilder(this)
                    .setMessage(resources.getString(R.string.title_body_empty_dialog_message))
                    .setPositiveButton(resources.getString(R.string.cancel)) { dialog, which ->
                    }
                    .show()
            }
        }
    }
}