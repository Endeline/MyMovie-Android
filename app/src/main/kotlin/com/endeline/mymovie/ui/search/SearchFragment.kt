package com.endeline.mymovie.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    protected lateinit var viewModel: SearchViewModel

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater)

        DaggerViewModelComponent.builder().build().inject(this)

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count >= 3) {
                    s?.let {
                        viewModel.search(it.toString())
                    }
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            movieLiveData.observe(viewLifecycleOwner, Observer {
                onMovieSearchResult(it)
            })

            tvLiveData.observe(viewLifecycleOwner, Observer {
                onTvSearchResult(it)
            })

            personLiveData.observe(viewLifecycleOwner, Observer {
                onPersonSearchResult(it)
            })
        }
    }

    private fun onMovieSearchResult(collection: List<SearchItemUiModel>) = with(binding) {
        if (collection.isNotEmpty()) {
            val searchAdapter = SearchAdapter(onClick = {
                findNavController().navigate(
                    SearchFragmentDirections.toDetails(it)
                )
            })

            moviesTitle.visibility = View.VISIBLE

            with(moviesRecycle) {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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

            with(tvRecycle) {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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

            with(personRecycle) {
                setHasFixedSize(true)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = searchAdapter
                visibility = View.VISIBLE
            }

            searchAdapter.submitList(transform(collection))
        } else {
            personTitle.visibility = View.GONE
            personRecycle.visibility = View.GONE
        }
    }

    private fun transform(collection: List<SearchItemUiModel>) =
        mutableListOf<SearchItemUiModel>().apply {
            collection.forEach {
                if (MediaType.fromString(it.mediaType) == MediaType.person) {
                    if (!it.profilePath.isNullOrBlank()) {
                        add(it)
                    }
                } else if (!it.backdropPath.isNullOrBlank() && !it.posterPath.isNullOrBlank()) {
                    add(it)
                }
            }
        }

}
