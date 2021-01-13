package com.example.mymemoapp.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class Memo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "body") val body: String?,
    @ColumnInfo(name = "insert_date") val insertDate: String,
    @ColumnInfo(name = "update_date") val updateDate: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false
)