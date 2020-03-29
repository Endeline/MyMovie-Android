package com.endeline.mymovie.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.uimodels.MovieCollectionUiModel
import com.endeline.domain.uimodels.MovieDetailsUiModel
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel
import com.endeline.mymovie.databinding.DetailsFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.extensions.loadPosterImage
import com.endeline.mymovie.ui.adapters.SimilarRecommendationMovieAdapter
import com.endeline.mymovie.ui.adapters.VideoAdapter
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

        with(arguments?.getInt(MOVIE_ID_KEY, -1)!!) {
            viewModel.loadMovieDetails(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this@DetailsFragment::onDataLoaded,
                    Timber::e
                )

            viewModel.loadSimilarMovies(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this@DetailsFragment::onSimilarLoaded,
                    Timber::e
                )

            viewModel.loadRecommendedMovies(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this@DetailsFragment::onRecommendedLoaded,
                    Timber::e
                )

            viewModel.loadVideoLinks(this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this@DetailsFragment::onVideoLinksLoaded,
                    Timber::e
                )
        }
    }

    private fun onDataLoaded(uiModel: MovieDetailsUiModel) {
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
                voteAverage.visibility = View.VISIBLE

                rating.visibility = View.VISIBLE
            }

            with(uiModel.popularity) {
                popularity.text = this.toString()
                popularityTitle.visibility = View.VISIBLE
            }

            uiModel.genres?.let {
                it.forEach {
                    genresList.addView(TextView(root.context).apply {
                        text = it.name.toString()
                    })
                }

                genresTitle.visibility = View.VISIBLE
            }

            uiModel.productionCountries?.let {
                it.forEach {
                    countriesList.addView(TextView(root.context).apply {
                        text = it.name
                    })
                }

                countriesTitle.visibility = View.VISIBLE
            }

            uiModel.spokenLanguages?.let {
                it.forEach {
                    languageList.addView(TextView(root.context).apply {
                        text = it.name
                    })
                }

                languageTitle.visibility = View.VISIBLE
            }

            uiModel.productionCompanies?.let {
                it.forEach {
                    companiesList.addView(TextView(root.context).apply {
                        text = it.name
                    })
                }

                companiesTitle.visibility = View.VISIBLE
            }

            loadingProgress.visibility = View.GONE
            poster.visibility = View.VISIBLE
        }
    }

    private fun onSimilarLoaded(similarMovies: MovieCollectionUiModel) {
        with(binding) {
            similarRecycleView.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = SimilarRecommendationMovieAdapter(similarMovies.results)
            }

            similarTitle.visibility = View.VISIBLE
        }
    }

    private fun onRecommendedLoaded(recommendedMovies: MovieCollectionUiModel) {
        with(binding) {
            recommendedRecycleView.apply {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = SimilarRecommendationMovieAdapter(recommendedMovies.results)
            }

            recommendedTitle.visibility = View.VISIBLE
        }
    }

    private fun onVideoLinksLoaded(videoLinks: VideoLinkCollectionUiModel) {
        binding.moviesRecycleView.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = VideoAdapter(videoLinks.results)
        }
    }

}
