package ru.vladislavsumin.gifvs.ui.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar

    private val glideLoadListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            //Todo
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            progressBar.visibility = GONE
            return false
        }

    }


    private fun init() {
        LayoutInflater.from(this.context).inflate(R.layout.view_gif, this, true)
        descriptionText = findViewById(R.id.view_gif_description)
        image = findViewById(R.id.view_gif_image)
        progressBar = findViewById(R.id.view_gif_progress)

    }

    fun setGif(gif: Gif) {
        descriptionText.text = gif.description
        progressBar.visibility = VISIBLE

        Glide
            .with(this)
            .load(gif.gifURL)
            .centerCrop()
            .addListener(glideLoadListener)
            .into(image)
    }
}