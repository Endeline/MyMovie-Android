package com.endeline.movielibrary.ui.gui.details.movie

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.DetailsFragmentBinding
import com.endeline.movielibrary.di.ViewModelFactory
import com.endeline.movielibrary.di.components.DaggerAppComponent
import com.endeline.movielibrary.extensions.ifNotEmpty
import com.endeline.movielibrary.extensions.setViewsVisibility
import com.endeline.movielibrary.extensions.setupWithAdapter
import com.endeline.movielibrary.Constants.Collections.MINIMUM_COLLECTION_SIZE
import com.endeline.movielibrary.ui.common.carousel.ImagesCarouselAdapter
import com.endeline.movielibrary.ui.common.carousel.RecyclerViewAutoScroll
import com.endeline.movielibrary.ui.common.credits.CreditsAdapter
import com.endeline.movielibrary.ui.common.reviews.ReviewsAdapter
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject
    lateinit var imagesAdapter: ImagesCarouselAdapter

    @Inject
    lateinit var recyclerViewAutoScroll: RecyclerViewAutoScroll

    @Inject
    lateinit var reviewsAdapter: ReviewsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.DetailsViewModelFactory

    private val viewModel by viewModels<DetailsViewModel> {
        viewModelFactory
    }

    private val args by navArgs<DetailsFragmentArgs>()

    private val videoLinksAdapter = VideoAdapter { key, site ->
        findNavController().navigate(DetailsFragmentDirections.toVideo(key, site))
    }

    private val similarAdapter = MovieAdapter {
        findNavController().navigate(DetailsFragmentDirections.toMovieDetails(it))
    }

    private val recommendedAdapter = MovieAdapter {
        findNavController().navigate(DetailsFragmentDirections.toMovieDetails(it))
    }

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
        DaggerAppComponent.create().inject(this)

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
        reviewsAdapter.listener = { item ->
            findNavController().navigate(
                DetailsFragmentDirections.toReview(
                    item.authorDetails.userName,
                    item.content,
                    item.authorDetails.avatarPath,
                    item.authorDetails.rating
                )
            )
        }

        castAdapter.listener = { personId ->
            findNavController().navigate(DetailsFragmentDirections.toPerson(personId))
        }

        crewAdapter.listener = { personId ->
            findNavController().navigate(DetailsFragmentDirections.toPerson(personId))
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

    //TODO maybe to uiExtensions
    private fun onDataLoaded(title: View, container: LinearLayoutCompat, texts: List<String>) {
        title.visibility = View.VISIBLE

        texts.forEach {
            container.addView(TextView(container.context).apply {
                text = it
            })
        }
    }

    //TODO maybe to uiExtensions
    private fun <T : Any> onDataLoaded(
        items: List<T>,
        adapter: androidx.recyclerview.widget.ListAdapter<T, *>,
        vararg views: View
    ) {
        ifNotEmpty(items) {
            setViewsVisibility(View.VISIBLE, *views)
            adapter.submitList(it)
        }
    }

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
