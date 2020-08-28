package ru.vladislavsumin.gifvs.ui.gif

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import ru.vladislavsumin.gifvs.domain.GifManager
import ru.vladislavsumin.gifvs.entity.Gif
import ru.vladislavsumin.gifvs.ui.base.BasePresenter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GifPresenter @Inject constructor(
    private val gifManager: GifManager
) : BasePresenter<GifView>() {
    companion object {
        private val TAG = GifPresenter::class.java.simpleName
    }

    private var currentGif: Gif? = null
    private var loadGifDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadGifDisposable = gifManager.getLast()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showGif(it)
            }, {
                //TODO add err handling
                Log.e(TAG, "error on load  gif info", it)
            })
    }

    fun onClickNext() {
        val gif = currentGif ?: return
        loadGifDisposable?.dispose()
        loadGifDisposable = gifManager.getNext(gif)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                showGif(it)
            }, {
                //TODO add err handling
                Log.e(TAG, "error on load next gif info", it)
            })
    }

    private fun showGif(gif: Gif) {
        currentGif = gif
        viewState.setGif(gif)
    }


    override fun onDestroy() {
        loadGifDisposable?.dispose()
        super.onDestroy()
    }
}