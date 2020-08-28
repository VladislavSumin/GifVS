package ru.vladislavsumin.gifvs.ui.gif

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.vladislavsumin.gifvs.domain.GifManager
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

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        gifManager.getLast()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.setGif(it)
            }, {
                //TODO add err handling
                Log.e(TAG, "error on load  gif info", it)
            })
    }
}