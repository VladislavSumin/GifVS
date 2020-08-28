package ru.vladislavsumin.gifvs

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.vladislavsumin.gifvs.api.GifApi
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var gifApi: GifApi

    override fun onCreate() {
        super.onCreate()

        gifApi.getRandom()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("aaaa", it.toString())
            }, {
                Log.d("aaaa", "", it)
            })

    }
}