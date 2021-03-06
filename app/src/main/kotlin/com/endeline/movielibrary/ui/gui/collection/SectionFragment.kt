package com.endeline.movielibrary.ui.gui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.movielibrary.Constants.Duration.RECYCLER_VIEW_ITEM_DURATION
import com.endeline.movielibrary.Constants.Size.SMALL_POSTER_IMAGE_HEIGHT
import com.endeline.movielibrary.Constants.Size.SMALL_POSTER_IMAGE_WIDTH
import com.endeline.movielibrary.Constants.String.UNSUPPORTED_TYPE
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.databinding.SectionFragmentBinding
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import com.endeline.movielibrary.ui.extensions.ifLet
import dagger.android.support.DaggerFragment
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import javax.inject.Inject


class SectionFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by viewModels<SectionViewModel> {
        viewModelFactory
    }

    private val movieAdapter by lazy {
        arguments?.getString(COLLECTION_TYPE)?.let { type ->
            when (ProductType.valueOf(type)) {
                ProductType.MOVIE -> SectionAdapter {
                    findNavController().navigate(CollectionFragmentDirections.toMovieDetails(it))
                }
                ProductType.TV -> SectionAdapter(
                    viewHolderImageHeight = SMALL_POSTER_IMAGE_HEIGHT,
                    viewHolderImageWidth = SMALL_POSTER_IMAGE_WIDTH,
                    clickListener = { tvId ->
                        findNavController().navigate(CollectionFragmentDirections.toTvDetails(tvId))
                    }
                )
                else -> throw RuntimeException(UNSUPPORTED_TYPE)
            }
        }
    }

    private var _binding: SectionFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        ifLet(
            arguments?.getString(COLLECTION_TYPE),
            arguments?.getString(SECTION_TYPE)
        ) { (collectionType, sectionType) ->
            binding.recycleView.apply {
                setHasFixedSize(true)

                layoutManager =
                    if (ProductType.valueOf(collectionType) == ProductType.MOVIE) {
                        LinearLayoutManager(requireContext())
                    } else {
                        GridLayoutManager(requireContext(), SPAN_TWO_COLUMN)
                    }
                adapter = movieAdapter
                itemAnimator = SlideInUpAnimator().apply {
                    addDuration = RECYCLER_VIEW_ITEM_DURATION
                }
            }

            viewModel.loadSection(
                sectionType,
                ProductType.valueOf(collectionType)
            )
        }
    }

    private fun subscribeUi() {
        viewModel.items.observe(viewLifecycleOwner) {
            movieAdapter?.submitList(it)
        }
    }

    companion object {
        const val COLLECTION_TYPE = "COLLECTION_TYPE"
        const val SECTION_TYPE = "SECTION_TYPE"

        private const val SPAN_TWO_COLUMN = 2
    }
}