package com.endeline.movielibrary.ui.gui.details.tv

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
import com.endeline.movielibrary.Constants.Collections.MINIMUM_COLLECTION_SIZE
import com.endeline.movielibrary.databinding.TvFragmentBinding
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import com.endeline.movielibrary.ui.extensions.onDataLoaded
import com.endeline.movielibrary.ui.extensions.setViewsVisibility
import com.endeline.movielibrary.ui.extensions.setupWithAdapter
import com.endeline.movielibrary.ui.common.carousel.ImagesCarouselAdapter
import com.endeline.movielibrary.ui.common.carousel.RecyclerViewAutoScroll
import com.endeline.movielibrary.ui.common.credits.CreditsAdapter
import com.endeline.movielibrary.ui.common.video.VideoAdapter
import com.endeline.movielibrary.ui.common.movie.MovieAdapter
import com.endeline.movielibrary.ui.common.reviews.ReviewsAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TvDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var imagesAdapter: ImagesCarouselAdapter

    @Inject
    lateinit var recyclerViewAutoScroll: RecyclerViewAutoScroll

    @Inject
    lateinit var seasonAdapter: SeasonAdapter

    @Inject
    lateinit var videoLinksAdapter: VideoAdapter

    @Inject
    lateinit var reviewsAdapter: ReviewsAdapter

    private val viewModel by viewModels<TvDetailsViewModel> {
        viewModelFactory
    }

    private val args by navArgs<TvDetailsFragmentArgs>()

    //todo di
    private val similarAdapter = MovieAdapter()

    //todo di
    private val recommendedAdapter = MovieAdapter()

    //TODO di
    private val castAdapter = CreditsAdapter()

    //TODO di
    private val crewAdapter = CreditsAdapter()

    private var _binding: TvFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponent()
        subscribeUi()

        viewModel.loadData(args.tvId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerViewAutoScroll.stopScrolling()
        _binding = null
    }

    private fun setComponent() = with(binding) {
        similarAdapter.listener = { id ->
            findNavController().navigate(TvDetailsFragmentDirections.toTvDetails(id))
        }

        recommendedAdapter.listener = { id ->
            findNavController().navigate(TvDetailsFragmentDirections.toTvDetails(id))
        }

        videoLinksAdapter.listener = { key, site ->
            findNavController().navigate(TvDetailsFragmentDirections.toVideo(key, site))
        }

        seasonAdapter.listener = { seasonId ->
            //todo implement
        }

        castAdapter.listener = { personId ->
            findNavController().navigate(TvDetailsFragmentDirections.toPerson(personId))
        }

        crewAdapter.listener = { personId ->
            findNavController().navigate(TvDetailsFragmentDirections.toPerson(personId))
        }

        reviewsAdapter.listener = { item ->
            findNavController().navigate(
                TvDetailsFragmentDirections.toReview(
                    item.authorDetails.userName,
                    item.content,
                    item.authorDetails.avatarPath,
                    item.authorDetails.rating
                )
            )
        }

        backdropsRecycler.setupWithAdapter(imagesAdapter)
        seasonRecycler.setupWithAdapter(seasonAdapter)
        moviesRecycler.setupWithAdapter(videoLinksAdapter)
        similarRecycler.setupWithAdapter(similarAdapter)
        recommendedRecycler.setupWithAdapter(recommendedAdapter)
        castRecycler.setupWithAdapter(castAdapter)
        crewRecycler.setupWithAdapter(crewAdapter)
        reviewsRecycler.setupWithAdapter(reviewsAdapter)

        PagerSnapHelper().apply {
            attachToRecyclerView(backdropsRecycler)
        }
    }

    private fun subscribeUi() = with(viewModel) {
        backdrops.observe(viewLifecycleOwner) { images ->
            onImagesLoaded(images)
        }

        voteAverage.observe(viewLifecycleOwner) { voteAverage ->
            onVoteAverageLoaded(voteAverage)
        }

        //todo extensions/function
        title.observe(viewLifecycleOwner) { title ->
            binding.title.apply {
                text = title
                visibility = View.VISIBLE
            }
        }

        //todo extensions/function
        tagline.observe(viewLifecycleOwner) { tagline ->
            binding.tagline.apply {
                text = tagline
                visibility = View.VISIBLE
            }
        }

        //todo extensions/function
        description.observe(viewLifecycleOwner) { description ->
            binding.description.apply {
                text = description
                visibility = View.VISIBLE
            }
        }

        //todo extensions/function
        popularity.observe(viewLifecycleOwner) { popularity ->
            binding.popularity.text = popularity.toString()
            binding.popularityTitle.visibility = View.VISIBLE
        }

        //todo extensions/function
        status.observe(viewLifecycleOwner) { status ->
            binding.status.text = status
            binding.statusTitle.visibility = View.VISIBLE
        }

        //todo extensions/function
        firstAirDate.observe(viewLifecycleOwner) { date ->
            setViewsVisibility(View.VISIBLE, binding.firstAirDate, binding.firstAirDateTitle)
            binding.firstAirDate.text = date
        }

        //todo extensions/function
        lastAirDate.observe(viewLifecycleOwner) { date ->
            setViewsVisibility(View.VISIBLE, binding.lastAirDate, binding.lastAirDateTitle)
            binding.lastAirDate.text = date
        }

        //todo extensions/function
        seasonCount.observe(viewLifecycleOwner) { count ->
            setViewsVisibility(View.VISIBLE, binding.seasonCount, binding.seasonCountTitle)
            binding.seasonCount.text = count.toString()
        }

        //todo extensions/function
        episodeCount.observe(viewLifecycleOwner) { count ->
            setViewsVisibility(View.VISIBLE, binding.episodeCount, binding.episodeCountTitle)
            binding.episodeCount.text = count.toString()
        }

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

        seasons.observe(viewLifecycleOwner) { seasons ->
            onDataLoaded(seasons, seasonAdapter, binding.seasonRecycler, binding.seasonTitle)
        }

        videoLinks.observe(viewLifecycleOwner) { links ->
            onDataLoaded(links, videoLinksAdapter, binding.moviesTitle, binding.moviesRecycler)
        }

        similar.observe(viewLifecycleOwner) {
            onDataLoaded(it, similarAdapter, binding.similarTitle, binding.similarRecycler)
        }

        cast.observe(viewLifecycleOwner) {
            onDataLoaded(it, castAdapter, binding.castTitle, binding.castRecycler)
        }

        crew.observe(viewLifecycleOwner) {
            onDataLoaded(it, crewAdapter, binding.crewTitle, binding.crewRecycler)
        }

        reviews.observe(viewLifecycleOwner) {
            onDataLoaded(it, reviewsAdapter, binding.reviewsTitle, binding.reviewsRecycler)
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

    //todo extensions?
    private fun onImagesLoaded(images: List<ImageUiModel>) = with(binding) {
        backdropsRecycler.visibility = View.VISIBLE
        imagesAdapter.submitList(images)

        if (images.size > MINIMUM_COLLECTION_SIZE) {
            indicator.attachToRecyclerView(backdropsRecycler)
            indicator.visibility = View.VISIBLE
            recyclerViewAutoScroll.setup(binding.backdropsRecycler, imagesAdapter.itemCount)
            recyclerViewAutoScroll.startScrolling()
        }
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
}
