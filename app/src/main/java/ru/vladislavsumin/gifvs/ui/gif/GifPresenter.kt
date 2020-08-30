package ru.vladislavsumin.gifvs.ui.gif

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
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
    private var lastSuccessLoadedGifPosition = Int.MIN_VALUE
    private var loadGifDisposable: Disposable? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onCLickRetry()
    }

    fun onClickNext() {
        val currentPosition = currentGif?.position ?: lastSuccessLoadedGifPosition
        val gif = currentGif ?: return
        loadGif(gifManager.getByPosition(currentPosition + 1), AnimationDirection.NEXT)
    }

    fun onClickBack() {
        val gif = currentGif
        val previousGifPosition = if (gif != null) {
            if (gif.position == 0) return
            else gif.position - 1
        } else {
            if (lastSuccessLoadedGifPosition <= 0) return
            else lastSuccessLoadedGifPosition
        }

        loadGif(gifManager.getByPosition(previousGifPosition), AnimationDirection.PREVIOUS)
    }

    private fun loadGif(gifSource: Single<Gif>, animationDirection: AnimationDirection) {
        viewState.setState(
            GifViewState(
                GifLoadingState.LOADING,
                isBackButtonEnabled = lastSuccessLoadedGifPosition > 0,
                animationDirection = animationDirection
            )
        )
        currentGif = null
        loadGifDisposable?.dispose()
        loadGifDisposable = gifSource
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::showGif, this::showLoadingError)
    }

    private fun showGif(gif: Gif) {
        currentGif = gif
        lastSuccessLoadedGifPosition = gif.position
        viewState.setState(
            GifViewState(
                state = GifLoadingState.LOADED,
                isBackButtonEnabled = gif.position > 0,
                gif = gif
            )
        )
    }

    private fun showLoadingError(e: Throwable) {
        Log.e(TAG, "error on load gif info", e)
        viewState.setState(
            GifViewState(
                state = GifLoadingState.ERROR,
                isBackButtonEnabled = lastSuccessLoadedGifPosition > 0
            )
        )
    }


    override fun onDestroy() {
        loadGifDisposable?.dispose()
        super.onDestroy()
    }

    fun onCLickRetry() {
        loadGif(gifManager.getLast(), AnimationDirection.NONE)
    }
}