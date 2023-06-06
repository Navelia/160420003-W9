package com.ncents.todoappweek8.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo (
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    //alasan isdone menggunakan integer bukan boolean dikarenakan sql lite tidak memiliki tipe data boolean
    @ColumnInfo(name="is_done")
    var isdone:Int = 0
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}