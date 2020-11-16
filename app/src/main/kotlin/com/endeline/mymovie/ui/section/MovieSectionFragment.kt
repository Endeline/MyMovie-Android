package com.endeline.mymovie.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.ProductType
import com.endeline.mymovie.databinding.SectionFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.ui.Constants
import com.endeline.mymovie.ui.adapters.ProductAdapter
import com.endeline.mymovie.ui.collection.CollectionFragmentDirections
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class MovieSectionFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory.SectionViewModelFactory =
        ViewModelFactory.SectionViewModelFactory()

    private val viewModel by viewModels<MovieSectionViewModel> {
        viewModelFactory
    }

    private val movieAdapter = ProductAdapter {
        findNavController().navigate(CollectionFragmentDirections.toDetails(it))
    }

    private var _binding: SectionFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SectionFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComponent()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupComponent() {
        binding.recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = Constants.Animation.RECYCLER_VIEW_ITEM_DURATION
            }
        }

        arguments?.getString(MOVIE_SECTION_TYPE)?.let {
            viewModel.loadSection(it, ProductType.movie)
        }
    }

    private fun subscribeUi() {
        viewModel.items.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    companion object {
        const val MOVIE_SECTION_TYPE = "MOVIE_SECTION_TYPE"
    }
}