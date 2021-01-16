package com.endeline.movielibrary.ui.gui.details.tv

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.PagerSnapHelper
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.Constants.Collections.MINIMUM_COLLECTION_SIZE
import com.endeline.movielibrary.databinding.TvFragmentBinding
import com.endeline.movielibrary.di.ViewModelFactory
import com.endeline.movielibrary.di.components.DaggerAppComponent
import com.endeline.movielibrary.extensions.setViewsVisibility
import com.endeline.movielibrary.extensions.setupWithAdapter
import com.endeline.movielibrary.ui.common.carousel.ImagesCarouselAdapter
import com.endeline.movielibrary.ui.common.carousel.RecyclerViewAutoScroll
import timber.log.Timber
import javax.inject.Inject

class TvDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.TvDetailsViewModelFactory

    @Inject
    lateinit var imagesAdapter: ImagesCarouselAdapter

    @Inject
    lateinit var recyclerViewAutoScroll: RecyclerViewAutoScroll

    @Inject
    lateinit var seasonAdapter: SeasonAdapter

    private val viewModel by viewModels<TvDetailsViewModel> {
        viewModelFactory
    }

    private val args by navArgs<TvDetailsFragmentArgs>()

    private var _binding: TvFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerAppComponent.create().inject(this)

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
        seasonAdapter.listener = { seasonId ->
            //TODO implement
        }

        backdropsRecycler.setupWithAdapter(imagesAdapter)

        PagerSnapHelper().apply {
            attachToRecyclerView(backdropsRecycler)
        }

        seasonRecycler.setupWithAdapter(seasonAdapter)
    }

    private fun subscribeUi() = with(viewModel) {
        backdrops.observe(viewLifecycleOwner) { images ->
            onImagesLoaded(images)
        }

        voteAverage.observe(viewLifecycleOwner) { voteAverage ->
            onVoteAverageLoaded(voteAverage)
        }

        title.observe(viewLifecycleOwner) { title ->
            binding.title.apply {
                text = title
                visibility = View.VISIBLE
            }
        }

        tagline.observe(viewLifecycleOwner) { tagline ->
            binding.tagline.apply {
                text = tagline
                visibility = View.VISIBLE
            }
        }

        popularity.observe(viewLifecycleOwner) { popularity ->
            binding.popularity.text = popularity.toString()
            binding.popularityTitle.visibility = View.VISIBLE
        }

        status.observe(viewLifecycleOwner) { status ->
            binding.status.text = status
            binding.statusTitle.visibility = View.VISIBLE
        }

        firstAirDate.observe(viewLifecycleOwner) { date ->
            setViewsVisibility(View.VISIBLE, binding.firstAirDate, binding.firstAirDateTitle)
            binding.firstAirDate.text = date
        }

        lastAirDate.observe(viewLifecycleOwner) { date ->
            setViewsVisibility(View.VISIBLE, binding.lastAirDate, binding.lastAirDateTitle)
            binding.lastAirDate.text = date
        }

        seasonCount.observe(viewLifecycleOwner) { count ->
            setViewsVisibility(View.VISIBLE, binding.seasonCount, binding.seasonCountTitle)
            binding.seasonCount.text = count.toString()
        }

        episodeCount.observe(viewLifecycleOwner) { count ->
            setViewsVisibility(View.VISIBLE, binding.episodeCount, binding.episodeCountTitle)
            binding.episodeCount.text = count.toString()
        }

        description.observe(viewLifecycleOwner) { description ->
            binding.description.apply {
                text = description
                visibility = View.VISIBLE
            }
        }

        seasons.observe(viewLifecycleOwner) { seasons ->
            binding.seasonRecycler.visibility = View.VISIBLE
            seasonAdapter.submitList(seasons)

            seasons.forEach {
                Timber.d("Wazne ${it.name} ${it.posterPath}")
            }
        }
    }

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
