package ru.vladislavsumin.gifvs.ui.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_gif.*
import moxy.MvpAppCompatFragment
import moxy.MvpFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.vladislavsumin.gifvs.R
import ru.vladislavsumin.gifvs.entity.Gif
import javax.inject.Inject


@AndroidEntryPoint
class GifFragment : MvpAppCompatFragment(), GifView {
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
        fragment_gif_btn_next.setOnClickListener { presenter.onClickNext() }
        fragment_gif_btn_back.setOnClickListener { presenter.onClickBack() }
    }

    override fun setState(state: GifViewState) {
        when (state.state) {
            GifLoadingState.LOADING -> fragment_gif_card.showLoadingState()
            GifLoadingState.LOADED -> fragment_gif_card.setGif(state.gif!!)
            GifLoadingState.ERROR -> fragment_gif_card.showLoadingErrorState()
        }
        fragment_gif_btn_back.isEnabled = state.isBackButtonEnabled
    }
}