package com.endeline.movielibrary.ui.gui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.endeline.movielibrary.databinding.SearchFragmentBinding
import com.endeline.movielibrary.ui.extensions.setupWithAdapterAndRemoveAnimation
import com.endeline.movielibrary.Constants.String.MINIMUM_TEXT_SIZE_TO_SEARCH
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import com.endeline.movielibrary.ui.extensions.onDataLoaded
import dagger.android.support.DaggerFragment
import javax.inject.Inject

//todo feature create tabs ??
class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by viewModels<SearchViewModel> {
        viewModelFactory
    }

    //TODO di?
    private val personAdapter = SearchAdapter { personId ->
        findNavController().navigate(SearchFragmentDirections.toPerson(personId))
    }

    //TODO di?
    private val tvAdapter = SearchAdapter {
        findNavController().navigate(SearchFragmentDirections.toTvDetails(it))
    }

    //TODO di?
    private val movieAdapter = SearchAdapter {
        findNavController().navigate(SearchFragmentDirections.toMovieDetails(it))
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        personRecycle.setupWithAdapterAndRemoveAnimation(personAdapter)
        tvRecycle.setupWithAdapterAndRemoveAnimation(tvAdapter)
        moviesRecycle.setupWithAdapterAndRemoveAnimation(movieAdapter)
    }

    private fun subscribeUi() = with(viewModel) {
        movie.observe(viewLifecycleOwner) {
            onDataLoaded(it, movieAdapter, binding.moviesTitle, binding.moviesRecycle)
        }

        tv.observe(viewLifecycleOwner) {
            onDataLoaded(it, tvAdapter, binding.tvTitle, binding.tvRecycle)
        }

        person.observe(viewLifecycleOwner) {
            onDataLoaded(it, personAdapter, binding.personTitle, binding.personRecycle)
        }
    }
}
