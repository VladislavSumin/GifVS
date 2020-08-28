package ru.vladislavsumin.gifvs.ui.gif

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.vladislavsumin.gifvs.entity.Gif

interface GifView : MvpView {
    @AddToEndSingle
    fun setGif(gif: Gif)
}