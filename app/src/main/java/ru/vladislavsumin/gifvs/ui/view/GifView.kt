package ru.vladislavsumin.gifvs.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import ru.vladislavsumin.gifvs.R
import ru.vladislavsumin.gifvs.entity.Gif

class GifView : FrameLayout {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    private lateinit var descriptionText: TextView


    private fun init() {
        LayoutInflater.from(this.context).inflate(R.layout.view_gif, this, true)
        descriptionText = findViewById(R.id.view_gif_description)
    }

    fun setGif(gif: Gif) {
        descriptionText.text = gif.description
    }
}