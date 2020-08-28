package ru.vladislavsumin.gifvs.ui.gif

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.vladislavsumin.gifvs.api.GifApi
import ru.vladislavsumin.gifvs.ui.base.BasePresenter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifPresenter @Inject constructor(
    private val gifApi: GifApi
) : BasePresenter<GifView>() {
    companion object {
        private val TAG = GifPresenter::class.java.simpleName
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        gifApi.getRandom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setGif(it)
            }, {
                //TODO add err handling
                Log.e(TAG, "error on load  gif info", it)
            })
    }
}