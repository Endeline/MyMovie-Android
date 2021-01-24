package com.endeline.movielibrary.ui.gui.details.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.DetailsFragmentBinding
import com.endeline.movielibrary.ui.extensions.setViewsVisibility
import com.endeline.movielibrary.ui.extensions.setupWithAdapter
import com.endeline.movielibrary.Constants.Collections.MINIMUM_COLLECTION_SIZE
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import com.endeline.movielibrary.ui.extensions.onDataLoaded
import com.endeline.movielibrary.ui.common.carousel.ImagesCarouselAdapter
import com.endeline.movielibrary.ui.common.carousel.RecyclerViewAutoScroll
import com.endeline.movielibrary.ui.common.credits.CreditsAdapter
import com.endeline.movielibrary.ui.common.movie.MovieAdapter
import com.endeline.movielibrary.ui.common.reviews.ReviewsAdapter
import com.endeline.movielibrary.ui.common.video.VideoAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var imagesAdapter: ImagesCarouselAdapter

    @Inject
    lateinit var recyclerViewAutoScroll: RecyclerViewAutoScroll

    @Inject
    lateinit var reviewsAdapter: ReviewsAdapter

    @Inject
    lateinit var viewModelFactoryMovie: ViewModelProviderFactory

    @Inject
    lateinit var videoLinksAdapter: VideoAdapter

    private val viewModel by viewModels<MovieDetailsViewModel> {
        viewModelFactoryMovie
    }

    private val args by navArgs<MovieDetailsFragmentArgs>()

    //todo di
    private val similarAdapter = MovieAdapter()

    //todo di
    private val recommendedAdapter = MovieAdapter()

    //TODO di
    private val castAdapter = CreditsAdapter()

    //TODO di
    private val crewAdapter = CreditsAdapter()

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponent()
        subscribeUi()

        viewModel.loadMovieData(args.movieId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerViewAutoScroll.stopScrolling()
        _binding = null
    }

    private fun setComponent() = with(binding) {
        similarAdapter.listener = { id ->
            findNavController().navigate(MovieDetailsFragmentDirections.toMovieDetails(id))
        }

        recommendedAdapter.listener =  { id ->
            findNavController().navigate(MovieDetailsFragmentDirections.toMovieDetails(id))
        }

        videoLinksAdapter.listener = { key, site ->
            findNavController().navigate(MovieDetailsFragmentDirections.toVideo(key, site))
        }

        reviewsAdapter.listener = { item ->
            findNavController().navigate(
                MovieDetailsFragmentDirections.toReview(
                    item.authorDetails.userName,
                    item.content,
                    item.authorDetails.avatarPath,
                    item.authorDetails.rating
                )
            )
        }

        castAdapter.listener = { personId ->
            findNavController().navigate(MovieDetailsFragmentDirections.toPerson(personId))
        }

        crewAdapter.listener = { personId ->
            findNavController().navigate(MovieDetailsFragmentDirections.toPerson(personId))
        }

        similarRecycler.setupWithAdapter(similarAdapter)
        recommendedRecycler.setupWithAdapter(recommendedAdapter)
        moviesRecycler.setupWithAdapter(videoLinksAdapter)
        backdropsRecycler.setupWithAdapter(imagesAdapter)
        reviewsRecycler.setupWithAdapter(reviewsAdapter)
        castRecycler.setupWithAdapter(castAdapter)
        crewRecycler.setupWithAdapter(crewAdapter)

        PagerSnapHelper().apply {
            attachToRecyclerView(backdropsRecycler)
        }
    }

    private fun subscribeUi() = with(viewModel) {
        onDataLoaded.observe(viewLifecycleOwner) { loaded ->
            if (loaded) {
                binding.loadingProgress.visibility = View.GONE
            }
        }

        content.observe(viewLifecycleOwner) { onContentLoaded(it) }

        voteAverage.observe(viewLifecycleOwner) { onVoteAverageLoaded(it) }

        popularity.observe(viewLifecycleOwner) { onPopularityLoaded(it) }

        backdrops.observe(viewLifecycleOwner) { onBackdropsLoaded(it) }

        genres.observe(viewLifecycleOwner) {
            onDataLoaded(binding.genresTitle, binding.genresList, it)
        }

        spokenLanguages.observe(viewLifecycleOwner) {
            onDataLoaded(binding.languageTitle, binding.languageList, it)
        }

        productionCompanies.observe(viewLifecycleOwner) {
            onDataLoaded(binding.companiesTitle, binding.companiesList, it)
        }

        productionCountries.observe(viewLifecycleOwner) {
            onDataLoaded(binding.countriesTitle, binding.countriesList, it)
        }

        reviews.observe(viewLifecycleOwner) {
            onDataLoaded(it, reviewsAdapter, binding.reviewsTitle, binding.reviewsRecycler)
        }

        cast.observe(viewLifecycleOwner) {
            onDataLoaded(it, castAdapter, binding.castTitle, binding.castRecycler)
        }

        crew.observe(viewLifecycleOwner) {
            onDataLoaded(it, crewAdapter, binding.crewTitle, binding.crewRecycler)
        }

        similar.observe(viewLifecycleOwner) {
            onDataLoaded(it, similarAdapter, binding.similarTitle, binding.similarRecycler)
        }

        videoLinks.observe(viewLifecycleOwner) {
            onDataLoaded(it, videoLinksAdapter, binding.moviesTitle, binding.moviesRecycler)
        }

        recommended.observe(viewLifecycleOwner) {
            onDataLoaded(
                it,
                recommendedAdapter,
                binding.recommendedTitle,
                binding.recommendedRecycler
            )
        }
    }

    private fun onContentLoaded(content: Pair<String, String>) = with(binding) {
        title.text = content.first
        description.text = content.second
    }

    //todo extensions?
    private fun onVoteAverageLoaded(vote: Double) = with(binding) {
        setViewsVisibility(View.VISIBLE, rating, voteAverage)

        rating.apply {
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
        }
    }

    private fun onPopularityLoaded(popularityCount: Double) = with(binding) {
        popularity.text = popularityCount.toString()
        popularityTitle.visibility = View.VISIBLE
    }

    //todo extensions?
    private fun onBackdropsLoaded(backdropsList: List<ImageUiModel>) = with(binding) {
        backdropsRecycler.visibility = View.VISIBLE
        imagesAdapter.submitList(backdropsList)

        if (backdropsList.size > MINIMUM_COLLECTION_SIZE) {
            indicator.attachToRecyclerView(backdropsRecycler)
            indicator.visibility = View.VISIBLE
            recyclerViewAutoScroll.setup(binding.backdropsRecycler, imagesAdapter.itemCount)
            recyclerViewAutoScroll.startScrolling()
        }
    }
}
