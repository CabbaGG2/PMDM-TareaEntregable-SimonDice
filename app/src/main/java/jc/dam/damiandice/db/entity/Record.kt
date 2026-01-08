package jc.dam.damiandice.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_table")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val score: Int,
    val time: String,
    val timestamp: Long
)