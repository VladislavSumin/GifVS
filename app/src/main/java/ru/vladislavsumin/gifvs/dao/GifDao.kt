package ru.vladislavsumin.gifvs.dao

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Maybe
import ru.vladislavsumin.gifvs.entity.Gif

@Dao
interface GifDao {
    //    @Query("SELECT * FROM entities where position = :position LIMIT 1")
//    fun getByPosition(position: Int): Maybe<Gif>
//
    @Query("SELECT * FROM entities ORDER BY position LIMIT 1")
    fun getLast(): Maybe<Gif>

}