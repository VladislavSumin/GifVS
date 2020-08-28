package ru.vladislavsumin.gifvs.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import ru.vladislavsumin.gifvs.entity.Gif
import java.util.*

@Dao
interface GifDao {
    @Query("SELECT * FROM entities where position = :position LIMIT 1")
    fun getByPositionRx(position: Int): Maybe<Gif>

    @Query("SELECT * FROM entities ORDER BY position DESC LIMIT 1")
    fun getLastRx(): Maybe<Gif>

    @Query("SELECT * FROM entities ORDER BY position DESC LIMIT 1")
    fun getLast(): Optional<Gif>

    @Insert
    fun saveRx(gif: Gif): Completable

    @Insert
    fun save(gif: Gif)

    fun addNewEntityRx(gif: Gif): Single<Gif> {
        return Single.fromCallable { addNewEntity(gif) }
    }

    @Transaction
    fun addNewEntity(gif: Gif): Gif {
        val last = getLast()
        val position = if (last.isPresent) last.get().position + 1 else 0
        val gif1 = gif.copy(position = position)
        save(gif1)
        return gif1
    }
}