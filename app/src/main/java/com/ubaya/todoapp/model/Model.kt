package com.ubaya.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int
    ) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}
