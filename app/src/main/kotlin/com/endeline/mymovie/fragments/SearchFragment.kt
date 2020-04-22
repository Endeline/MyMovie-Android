package com.endeline.mymovie.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.ui.adapters.SearchAdapter
import com.endeline.mymovie.viewmodels.SearchViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerViewModelComponent.builder().build().inject(this)

        init()
    }

    @SuppressLint("CheckResult")
    private fun init() {

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

        viewModel.movieSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onMovieSearchResult,
                Timber::e
            )

        viewModel.tvSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onTvSearchResult,
                Timber::e
            )

        viewModel.personSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                this::onPersonSearchResult,
                Timber::e
            )
    }

    private fun onMovieSearchResult(collection: List<SearchItemUiModel>) {
        with(binding) {
            if (collection.isNotEmpty()) {
                moviesTitle.visibility = View.VISIBLE

                with(moviesRecycle) {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = SearchAdapter(collection)
                    visibility = View.VISIBLE
                }
            } else {
                moviesTitle.visibility = View.GONE
                moviesRecycle.visibility = View.GONE
            }
        }
    }

    private fun onTvSearchResult(collection: List<SearchItemUiModel>) {
        with(binding) {
            if (collection.isNotEmpty()) {
                tvTitle.visibility = View.VISIBLE

                with(tvRecycle) {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = SearchAdapter(collection)
                    visibility = View.VISIBLE
                }
            } else {
                tvTitle.visibility = View.GONE
                tvRecycle.visibility = View.GONE
            }
        }
    }

    private fun onPersonSearchResult(collection: List<SearchItemUiModel>) {
        with(binding) {
            if (collection.isNotEmpty()) {
                personTitle.visibility = View.VISIBLE

                with(personRecycle) {
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter = SearchAdapter(collection)
                    visibility = View.VISIBLE
                }
            } else {
                personTitle.visibility = View.GONE
                personRecycle.visibility = View.GONE
            }

        }
    }
}
