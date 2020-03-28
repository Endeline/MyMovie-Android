package com.endeline.mymovie.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.endeline.domain.uimodels.MovieDetailsUiModel
import com.endeline.mymovie.databinding.DetailsFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.extensions.loadPosterImage
import com.endeline.mymovie.viewmodels.DetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class DetailsFragment : Fragment() {

    companion object {
        private const val MOVIE_ID_KEY = "movie_id"
    }

    //todo section ->
    // videos
    // recommendations
    // similar movies
    // reviews -> opinion
    // person -> find in api!!!

    @Inject
    protected lateinit var viewModel: DetailsViewModel

    private lateinit var binding: DetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater)

        DaggerViewModelComponent.builder().build().inject(this)

        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loadMovieDetails(arguments?.getInt(MOVIE_ID_KEY, -1)!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onDataLoaded,
                Timber::e
            )
    }

    private fun onDataLoaded(uiModel: MovieDetailsUiModel) {
        Timber.d("$uiModel")

        with(binding) {
            title.text = uiModel.title
            poster.loadPosterImage(uiModel.posterPath ?: "")
            description.text = uiModel.overview

            uiModel.voteAverage?.let {
                with(rating) {
                    max = 10f
                    progress = it.toFloat()
                    circleColor = Color.TRANSPARENT
                    circleProgressColor = when (it.toInt()) {
                        in 1..3 -> Color.RED
                        in 4..6 -> Color.YELLOW
                        else -> Color.GREEN

                    }
                }
                voteAverage.text = it.toInt().toString()
            }

            with(uiModel.popularity) {
                popularity.text = this.toString()
            }

            uiModel.genres?.let {
                it.forEach {
                    genresList.addView(TextView(root.context).apply {
                        text = it.name.toString()
                    })
                }
            }

            uiModel.productionCountries?.let {
                it.forEach {
                    countriesList.addView(TextView(root.context).apply {
                        text = it.name
                    })
                }
            }

            uiModel.spokenLanguages?.let {
                it.forEach {
                    languageList.addView(TextView(root.context).apply {
                        text = it.name
                    })
                }
            }

            uiModel.productionCompanies?.let {
                it.forEach {
                    companiesList.addView(TextView(root.context).apply {
                        text = it.name
                    })
                }
            }
        }

        changeVisibleAfterLoaded()

        viewModel.addMovieDetailsToCache(uiModel)
    }

    private fun changeVisibleAfterLoaded() {
        with(binding) {
            loadingProgress.visibility = View.GONE

            poster.visibility = View.VISIBLE
            rating.visibility = View.VISIBLE
            voteAverage.visibility = View.VISIBLE
            languageTitle.visibility = View.VISIBLE
            countriesTitle.visibility = View.VISIBLE
            genresTitle.visibility = View.VISIBLE
            popularityTitle.visibility = View.VISIBLE
            companiesTitle.visibility = View.VISIBLE
        }
    }

}
