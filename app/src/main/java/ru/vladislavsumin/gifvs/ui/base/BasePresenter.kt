package ru.vladislavsumin.gifvs.ui.base

import androidx.annotation.CallSuper
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    protected fun Disposable.disposeOnDestroy() {
        disposable.add(this)
    }
}