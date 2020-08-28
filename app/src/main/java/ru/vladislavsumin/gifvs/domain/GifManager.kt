package ru.vladislavsumin.gifvs.domain

import io.reactivex.rxjava3.core.Single
import ru.vladislavsumin.gifvs.entity.Gif

interface GifManager {
    fun getLast(): Single<Gif>
    fun getNext(previousGif: Gif): Single<Gif>
}