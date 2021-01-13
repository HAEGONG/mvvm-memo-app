package com.example.mymemoapp.view.adapter

import android.content.Intent
import android.text.TextUtils
import android.view.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymemoapp.R
import com.example.mymemoapp.model.entity.Memo
import com.example.mymemoapp.view.BaseCallInterface
import com.example.mymemoapp.view.MemoActivity
import com.example.mymemoapp.view.adapter.MemoListAdapter.MemoViewHolder
import kotlinx.android.synthetic.main.view_card_memo.view.*

class MemoListAdapter : ListAdapter<Memo, MemoViewHolder>(MemosComparator) {

    var listener: BaseCallInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, listener)
    }

    class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(memo: Memo, listener: BaseCallInterface?) {
            if (TextUtils.isEmpty(memo.title)) {
                itemView.memo_title.visibility = View.GONE
            } else {
                itemView.memo_title.visibility = View.VISIBLE
                itemView.memo_title.text = memo.title
            }

            if (TextUtils.isEmpty(memo.body)) {
                itemView.memo_body.visibility = View.GONE
            } else {
                itemView.memo_body.visibility = View.VISIBLE
                itemView.memo_body.text = memo.body
            }

            itemView.memo_date.text = memo.updateDate

            itemView.card_memo.isChecked = memo.favorite

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MemoActivity::class.java)
                intent.putExtra("id", memo.id)
                ContextCompat.startActivity(itemView.context, intent, null)
            }

            itemView.setOnLongClickListener {
                itemView.card_memo.isChecked = !memo.favorite
                listener?.getViewModel()?.setFavorite(memo.id, !memo.favorite)
                true
            }
        }

        companion object {
            fun create(parent: ViewGroup): MemoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_card_memo, parent, false)
                return MemoViewHolder(view)
            }
        }

    }



    companion object {
        private val MemosComparator = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}