package com.endeline.mymovie.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.mymovie.databinding.MovieFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.ui.adapters.MovieAdapter

class PopularFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory.PopularViewModelFactory =
        ViewModelFactory.PopularViewModelFactory()

    private val viewModel by viewModels<PopularViewModel> {
        viewModelFactory
    }

    private lateinit var binding: MovieFragmentBinding

    private val movieAdapter = MovieAdapter {
        findNavController().navigate(PopularFragmentDirections.toDetails(it))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComponent()
        subscribeUi()
    }

    private fun setupComponent() = with(binding) {
        recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }
    }

    private fun subscribeUi() = with(viewModel) {
        popularLiveData.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }
}
