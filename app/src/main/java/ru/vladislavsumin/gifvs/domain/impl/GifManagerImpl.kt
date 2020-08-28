package ru.vladislavsumin.gifvs.domain.impl

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.vladislavsumin.gifvs.api.GifApi
import ru.vladislavsumin.gifvs.dao.GifDao
import ru.vladislavsumin.gifvs.domain.GifManager
import ru.vladislavsumin.gifvs.entity.Gif
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifManagerImpl @Inject constructor(
    private val gifApi: GifApi,
    private val gifDao: GifDao
) : GifManager {

    override fun getLast(): Single<Gif> {
        return gifDao.getLastRx()
            .switchIfEmpty(loadNext())
            .subscribeOn(Schedulers.io())
    }

    override fun getNext(previousGif: Gif): Single<Gif> {
        return gifDao.getByPositionRx(previousGif.position + 1)
            .switchIfEmpty(loadNext())
            .subscribeOn(Schedulers.io())
    }

    private fun loadNext(): Single<Gif> {
        return gifApi.getRandom().flatMap(gifDao::addNewEntityRx)
    }

    override fun getByPosition(position: Int): Maybe<Gif> {
        return gifDao.getByPositionRx(position)
            .subscribeOn(Schedulers.io())
    }
}