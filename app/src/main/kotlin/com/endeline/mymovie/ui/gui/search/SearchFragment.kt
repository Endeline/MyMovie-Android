package com.endeline.mymovie.ui.gui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.uimodels.SearchUiModel.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.di.components.DaggerAppComponent
import com.endeline.mymovie.extensions.setViewsVisibility
import com.endeline.mymovie.ui.Constants.Animation.RECYCLER_VIEW_ITEM_DURATION
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import javax.inject.Inject

//todo feature create tabs ??
class SearchFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.SearchViewModelFactory

    private val viewModel by viewModels<SearchViewModel> {
        viewModelFactory
    }

    private val personAdapter = SearchAdapter {
        //todo create person details
    }

    private val tvAdapter = SearchAdapter {
        //todo create tv details or upgrade current details
    }

    private val movieAdapter = SearchAdapter {
        findNavController().navigate(SearchFragmentDirections.toDetails(it))
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        DaggerAppComponent.create().inject(this)

        _binding = SearchFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupUi() = with(binding) {
        searchInput.doOnTextChanged { text, _, _, _ ->
            text?.let { query ->
                if (query.length >= MINIMUM_TEXT_SIZE_TO_SEARCH) {
                    viewModel.search(query.toString())
                }
            }
        }

        personRecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = personAdapter
            itemAnimator = SlideInRightAnimator().apply {
                addDuration = RECYCLER_VIEW_ITEM_DURATION
                removeDuration = RECYCLER_VIEW_ITEM_DURATION
            }
        }

        tvRecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = tvAdapter
            itemAnimator = SlideInRightAnimator().apply {
                addDuration = RECYCLER_VIEW_ITEM_DURATION
                removeDuration = RECYCLER_VIEW_ITEM_DURATION
            }
        }

        moviesRecycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = movieAdapter
            itemAnimator = SlideInRightAnimator().apply {
                addDuration = RECYCLER_VIEW_ITEM_DURATION
                removeDuration = RECYCLER_VIEW_ITEM_DURATION
            }
        }
    }

    private fun subscribeUi() = with(viewModel) {
        movieLiveData.observe(viewLifecycleOwner) {
            onMovieSearchResult(it)
        }

        tvLiveData.observe(viewLifecycleOwner) {
            onTvSearchResult(it)
        }

        personLiveData.observe(viewLifecycleOwner) {
            onPersonSearchResult(it)
        }
    }

    private fun onMovieSearchResult(collection: List<SearchItemUiModel>) = with(binding) {
        if (collection.isNotEmpty()) {
            setViewsVisibility(View.VISIBLE, moviesTitle, moviesRecycle)
            movieAdapter.submitList(transform(collection))
        } else {
            setViewsVisibility(View.GONE, moviesTitle, moviesRecycle)
        }
    }

    private fun onTvSearchResult(collection: List<SearchItemUiModel>) = with(binding) {
        if (collection.isNotEmpty()) {
            setViewsVisibility(View.VISIBLE, tvTitle, tvRecycle)
            tvAdapter.submitList(transform(collection))
        } else {
            setViewsVisibility(View.GONE, tvTitle, tvRecycle)
        }
    }

    private fun onPersonSearchResult(collection: List<SearchItemUiModel>) = with(binding) {
        if (collection.isNotEmpty()) {
            setViewsVisibility(View.VISIBLE, personTitle, personRecycle)
            personAdapter.submitList(transform(collection))
        } else {
            setViewsVisibility(View.GONE, personTitle, personRecycle)
        }
    }

    private fun transform(collection: List<SearchItemUiModel>) : List<SearchItemUiModel>{
        return collection.filter {
            it.profilePath.isNotBlank() || it.backdropPath.isNotBlank() && it.posterPath.isNotBlank()
        }
    }

    companion object {
        private const val MINIMUM_TEXT_SIZE_TO_SEARCH = 3
    }
}
