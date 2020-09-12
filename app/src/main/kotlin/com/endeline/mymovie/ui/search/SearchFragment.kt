package com.endeline.mymovie.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.uimodels.SearchAllUiModel.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory

class SearchFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory.SearchViewModelFactory =
        ViewModelFactory.SearchViewModelFactory()

    private val viewModel by viewModels<SearchViewModel> {
        viewModelFactory
    }

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        subscribeUi()
    }

    private fun setupUi() = with(binding) {
        searchInput.doOnTextChanged { text, _, _, _ ->
            text?.let { query ->
                if (query.length >= MINIMUM_TEXT_SIZE_TO_SEARCH) {
                    viewModel.search(query.toString())
                }
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
            val searchAdapter = SearchAdapter(onClick = {
                findNavController().navigate(SearchFragmentDirections.toDetails(it))
            })

            moviesTitle.visibility = View.VISIBLE

            moviesRecycle.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = searchAdapter
                visibility = View.VISIBLE
            }

            searchAdapter.submitList(transform(collection))
        } else {
            moviesTitle.visibility = View.GONE
            moviesRecycle.visibility = View.GONE
        }
    }

    private fun onTvSearchResult(collection: List<SearchItemUiModel>) = with(binding) {
        if (collection.isNotEmpty()) {
            val searchAdapter = SearchAdapter(onClick = {
                //todo create tv details or upgrade current details
            })

            tvTitle.visibility = View.VISIBLE

            tvRecycle.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = searchAdapter
                visibility = View.VISIBLE
            }

            searchAdapter.submitList(transform(collection))
        } else {
            tvTitle.visibility = View.GONE
            tvRecycle.visibility = View.GONE
        }
    }

    private fun onPersonSearchResult(collection: List<SearchItemUiModel>) = with(binding) {
        if (collection.isNotEmpty()) {
            val searchAdapter = SearchAdapter(onClick = {
                //todo create person details
            })

            personTitle.visibility = View.VISIBLE

            personRecycle.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = searchAdapter
                visibility = View.VISIBLE
            }

            searchAdapter.submitList(transform(collection))
        } else {
            personTitle.visibility = View.GONE
            personRecycle.visibility = View.GONE
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
