package com.endeline.mymovie.ui.details

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.uimodels.*
import com.endeline.mymovie.databinding.DetailsFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.extensions.loadPosterImage

class DetailsFragment : Fragment() {

    //todo section ->
    // reviews -> opinion
    // person -> find in api!!!

    private val viewModelFactory: ViewModelFactory.DetailsViewModelFactory =
        ViewModelFactory.DetailsViewModelFactory()

    private val viewModel by viewModels<DetailsViewModel>(factoryProducer = { viewModelFactory })

    private val args by navArgs<DetailsFragmentArgs>()

    private lateinit var binding: DetailsFragmentBinding

    private var movieId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsFragmentBinding.inflate(inflater)

        movieId = args.movieId

        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        subscribeUi()

        viewModel.loadMovieData(movieId)
    }

    private fun subscribeUi() = with(viewModel) {
        getSimilarLiveData().observe(viewLifecycleOwner, Observer { onSimilarLoaded(it) })

        getRecommendedLiveData().observe(viewLifecycleOwner, Observer { onRecommendedLoaded(it) })

        getVideoLinksLiveData().observe(viewLifecycleOwner, Observer { onVideoLinksLoaded(it) })

        getContentLiveData().observe(viewLifecycleOwner, Observer { onContentLoaded(it) })

        getPosterLiveData().observe(viewLifecycleOwner, Observer { onPosterLoaded(it) })

        getVoteAverageLiveData().observe(viewLifecycleOwner, Observer { onVoteAverageLoaded(it) })

        getPopularityLiveData().observe(viewLifecycleOwner, Observer { onPopularityLoaded(it) })

        getGenresLiveData().observe(viewLifecycleOwner, Observer { onGenresLoaded(it) })

        getOnDataLoadedLive().observe(viewLifecycleOwner, Observer { onDataLoaded(true) })

        getProductionCountriesLiveData().observe(
            viewLifecycleOwner,
            Observer { onProductionCountriesDataLoaded(it) })

        getSpokenLanguageLiveData().observe(
            viewLifecycleOwner,
            Observer { onSpokenLanguageLoaded(it) })

        getProductionCompaniesLiveData().observe(
            viewLifecycleOwner,
            Observer { onProductionCompaniesLoaded(it) })
    }

    private fun onContentLoaded(content: Pair<String, String>) {
        with(binding) {
            title.text = content.first
            description.text = content.second
        }
    }

    private fun onPosterLoaded(posterPath: String) = with(binding) {
        poster.apply {
            loadPosterImage(posterPath)
            visibility = View.VISIBLE
        }
    }

    private fun onVoteAverageLoaded(vote: Double) = with(binding) {
        rating.apply {
            visibility = View.VISIBLE
            max = 10f
            progress = vote.toFloat()
            circleColor = Color.TRANSPARENT
            circleProgressColor = when (vote.toInt()) {
                in 1..3 -> Color.RED
                in 4..6 -> Color.YELLOW
                else -> Color.GREEN
            }
        }

        voteAverage.apply {
            text = vote.toInt().toString()
            visibility = View.VISIBLE
        }
    }

    private fun onPopularityLoaded(popularityCount: Double) = with(binding) {
        popularity.text = popularityCount.toString()
        popularityTitle.visibility = View.VISIBLE
    }

    private fun onGenresLoaded(genres: List<GenresUiModel>) = with(binding) {
        genresTitle.visibility = View.VISIBLE

        genres.forEach {
            genresList.addView(TextView(root.context).apply {
                text = it.name.toString()
            })
        }
    }

    private fun onProductionCountriesDataLoaded(productionCountries: List<ProductionCountriesUiModel>) =
        with(binding) {
            countriesTitle.visibility = View.VISIBLE

            productionCountries.forEach {
                countriesList.addView(TextView(root.context).apply {
                    text = it.name
                })
            }
        }

    private fun onSpokenLanguageLoaded(spokenLanguages: List<SpokenLanguagesUiModel>) =
        with(binding) {
            languageTitle.visibility = View.VISIBLE

            spokenLanguages.forEach {
                languageList.addView(TextView(root.context).apply {
                    text = it.name
                })
            }
        }

    private fun onProductionCompaniesLoaded(productionCompanies: List<ProductionCompaniesUiModel>) =
        with(binding) {
            productionCompanies.forEach {
                companiesTitle.visibility = View.VISIBLE

                companiesList.addView(TextView(root.context).apply {
                    text = it.name
                })
            }
        }

    @Suppress("SameParameterValue")
    private fun onDataLoaded(loaded: Boolean) = with(binding) {
        if (loaded) {
            loadingProgress.visibility = View.GONE
        }
    }

    private fun onSimilarLoaded(similarMovies: List<MovieCollectionItemUiModel>) = with(binding) {
        val movieAdapter = MovieAdapter(onClick = {
            findNavController().navigate(DetailsFragmentDirections.toDetails(it))
        })

        similarTitle.visibility = View.VISIBLE

        similarRecycleView.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = movieAdapter

            movieAdapter.submitList(similarMovies)
        }
    }

    private fun onRecommendedLoaded(recommendedMovies: List<MovieCollectionItemUiModel>) =
        with(binding) {
            val movieAdapter = MovieAdapter(onClick = {
                findNavController().navigate(DetailsFragmentDirections.toDetails(it))
            })

            recommendedTitle.visibility = View.VISIBLE

            recommendedRecycleView.apply {
                visibility = View.VISIBLE
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = movieAdapter

                movieAdapter.submitList(recommendedMovies)
            }
        }

    private fun onVideoLinksLoaded(videoLinks: List<VideoLinkDetailsUiModel>) = with(binding) {
        val videoAdapter = VideoAdapter(clickListener = { key, site ->
            findNavController().navigate(DetailsFragmentDirections.toVideo(key, site))
        })

        moviesRecycleView.apply {
            visibility = View.VISIBLE
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = videoAdapter
        }

        videoAdapter.submitList(videoLinks)
    }
}
