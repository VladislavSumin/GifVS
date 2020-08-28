package ru.vladislavsumin.gifvs.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.vladislavsumin.gifvs.entity.Gif


@Database(entities = [Gif::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database"
            ).build()
        }
    }

    abstract fun getGifDao(): GifDao
}