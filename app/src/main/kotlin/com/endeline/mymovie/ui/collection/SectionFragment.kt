package com.endeline.mymovie.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.domain.ProductType
import com.endeline.mymovie.databinding.SectionFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.extensions.ifLet
import com.endeline.mymovie.ui.Constants
import com.endeline.mymovie.ui.adapters.ProductAdapter
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class SectionFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory.SectionViewModelFactory =
        ViewModelFactory.SectionViewModelFactory()

    private val viewModel by viewModels<SectionViewModel> {
        viewModelFactory
    }

    private val movieAdapter by lazy {
        if (ProductType.valueOf(arguments?.getString(COLLECTION_TYPE)!!) == ProductType.movie) {
            ProductAdapter {
                findNavController().navigate(CollectionFragmentDirections.toDetails(it))
            }
        } else {
            ProductAdapter(
                viewHolderImageHeight = 150,
                viewHolderImageWidth = 100,
                clickListener = {
                    //todo create tv details
                    //findNavController().navigate(MoviesFragmentDirections.toDetails(it))
                }
            )
        }
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

        subscribeUi()
        setupComponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupComponent() {
        binding.recycleView.apply {
            setHasFixedSize(true)

            layoutManager =
                if (ProductType.valueOf(arguments?.getString(COLLECTION_TYPE)!!) == ProductType.movie) {
                    LinearLayoutManager(requireContext())
                } else {
                    GridLayoutManager(requireContext(), SPAN_TWO_COLUMN)
                }
            adapter = movieAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = Constants.Animation.RECYCLER_VIEW_ITEM_DURATION
            }
        }

        ifLet(
            arguments?.getString(COLLECTION_TYPE),
            arguments?.getString(SECTION_TYPE)
        ) { (collectionType, sectionType) ->
            viewModel.loadSection(
                sectionType,
                ProductType.valueOf(collectionType)
            )
        }
    }

    private fun subscribeUi() {
        viewModel.items.observe(viewLifecycleOwner) {
            movieAdapter.submitList(it)
        }
    }

    companion object {
        const val COLLECTION_TYPE = "COLLECTION_TYPE"
        const val SECTION_TYPE = "SECTION_TYPE"

        private const val SPAN_TWO_COLUMN = 2
    }
}