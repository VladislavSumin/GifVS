package ru.vladislavsumin.gifvs.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entities")
data class Gif(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 0,
    val description: String = "",
    val gifURL: String = "",
    val position: Int = 0
)