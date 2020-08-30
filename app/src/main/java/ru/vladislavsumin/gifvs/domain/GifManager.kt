package ru.vladislavsumin.gifvs.domain

import io.reactivex.rxjava3.core.Single
import ru.vladislavsumin.gifvs.entity.Gif

interface GifManager {
    fun getLast(): Single<Gif>

    /**
     *  To load new gif pass negative position
     *  @return gif by given position from database, if current position not exists, then load next random gif
     */
    fun getByPosition(position: Int): Single<Gif>
}