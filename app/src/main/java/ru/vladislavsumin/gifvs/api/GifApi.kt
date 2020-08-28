package ru.vladislavsumin.gifvs.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import ru.vladislavsumin.gifvs.entity.Gif

interface GifApi {

    @GET("random?json=true")
    fun getRandom(): Single<Gif>
}