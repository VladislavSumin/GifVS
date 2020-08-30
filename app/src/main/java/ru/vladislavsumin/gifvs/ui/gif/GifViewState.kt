package ru.vladislavsumin.gifvs.ui.gif

import ru.vladislavsumin.gifvs.entity.Gif

data class GifViewState(
    val state: GifLoadingState,
    val isBackButtonEnabled: Boolean,
    val gif: Gif? = null
)

enum class GifLoadingState {
    LOADING,
    LOADED,
    ERROR
}