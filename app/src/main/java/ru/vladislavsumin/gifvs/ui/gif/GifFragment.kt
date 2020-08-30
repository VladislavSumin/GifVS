package ru.vladislavsumin.gifvs.ui.gif

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gif.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.vladislavsumin.gifvs.R
import ru.vladislavsumin.gifvs.ui.view.GifCardView
import javax.inject.Inject


@AndroidEntryPoint
class GifFragment : MvpAppCompatFragment(), GifView {
    companion object {
        private const val ANIMATION_DURATION = 300L
    }

    private lateinit var primaryGifCardView: GifCardView
    private lateinit var secondaryGifCardView: GifCardView

    /**
     * This is not good solution to block any action when playing animation,
     * but I`m hav`t time to write normal solution with all animation cases
     */
    private var isAnimate = false

    @Inject
    @InjectPresenter
    lateinit var presenter: GifPresenter

    @ProvidePresenter
    fun providePresenter(): GifPresenter {
        return presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gif, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        primaryGifCardView = view.findViewById(R.id.fragment_gif_card_1)
        secondaryGifCardView = view.findViewById(R.id.fragment_gif_card_2)

        fragment_gif_btn_next.setOnClickListener {
            if (isAnimate) return@setOnClickListener
            presenter.onClickNext()
        }
        fragment_gif_btn_back.setOnClickListener {
            if (isAnimate) return@setOnClickListener
            presenter.onClickBack()
        }
    }

    override fun setState(state: GifViewState) {
        when (state.state) {
            GifLoadingState.LOADING -> {
                animate(state.animationDirection)
                primaryGifCardView.showLoadingState()
            }
            GifLoadingState.LOADED -> primaryGifCardView.setGif(state.gif!!)
            GifLoadingState.ERROR -> primaryGifCardView.showLoadingErrorState()
        }
        fragment_gif_btn_back.isEnabled = state.isBackButtonEnabled
    }


    private fun animate(animationDirection: AnimationDirection?) {
        if (animationDirection == AnimationDirection.NONE) return
        swapGifCard()
        secondaryGifCardView.translationX
        val dir = if (animationDirection == AnimationDirection.NEXT) 1 else -1

        ObjectAnimator.ofFloat(
            secondaryGifCardView,
            "translationX",
            0f,
            -view!!.width.toFloat() * dir
        ).apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = ANIMATION_DURATION
            doOnStart { this@GifFragment.isAnimate = true }
            doOnEnd {
                this@GifFragment.isAnimate = false
                secondaryGifCardView.visibility = View.GONE
            }
        }.start()

        ObjectAnimator.ofFloat(
            primaryGifCardView,
            "translationX",
            view!!.width.toFloat() * dir,
            0f
        ).apply {
            interpolator = AccelerateDecelerateInterpolator()
            doOnStart { primaryGifCardView.visibility = View.VISIBLE }
            duration = ANIMATION_DURATION
        }.start()
    }

    private fun swapGifCard() {
        val tmp = primaryGifCardView
        primaryGifCardView = secondaryGifCardView
        secondaryGifCardView = tmp
    }
}