package ru.vladislavsumin.gifvs.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entities")
data class Gif(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val description: String,
    val gifURL: String,
    val position: Int
)