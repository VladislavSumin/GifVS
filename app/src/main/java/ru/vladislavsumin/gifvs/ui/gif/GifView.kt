package ru.vladislavsumin.gifvs.ui.gif

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState
import ru.vladislavsumin.gifvs.entity.Gif

interface GifView : MvpView {
    @SingleState
    fun setState(state: GifViewState)
}