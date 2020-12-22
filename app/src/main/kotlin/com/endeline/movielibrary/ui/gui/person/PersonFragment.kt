package com.endeline.movielibrary.ui.gui.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.databinding.PersonFragmentBinding
import com.endeline.movielibrary.di.ViewModelFactory
import com.endeline.movielibrary.di.components.DaggerAppComponent
import com.endeline.movielibrary.extensions.*
import com.endeline.movielibrary.ui.common.poster.PosterImageAdapter
import javax.inject.Inject

class PersonFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.PersonViewModelFactory

    @Inject
    lateinit var posterImageAdapter: PosterImageAdapter

    private val viewModel by viewModels<PersonViewModel> {
        viewModelFactory
    }

    private val args by navArgs<PersonFragmentArgs>()

    private var _binding: PersonFragmentBinding? = null
    private val binding get() = _binding!!

    //todo di
    private val movieCastAdapter = CastAdapter()

    //todo di
    private val tvCastAdapter = CastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerAppComponent.create().inject(this)

        _binding =
            PersonFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponents()
        subscribeUi()

        viewModel.load(args.personId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setComponents() = with(binding) {
        posterImageAdapter.listener = {
            findNavController().navigate(PersonFragmentDirections.toPreviewImage(it))
        }

        movieCastAdapter.listener = {
            findNavController().navigate(PersonFragmentDirections.toMovieDetails(it))
        }

        tvCastAdapter.listener = {
            //todo navigation to tv
        }

        imageRecycler.setupWithAdapter(posterImageAdapter)
        movieCastRecycler.setupWithAdapter(movieCastAdapter)
        tvCastRecycler.setupWithAdapter(tvCastAdapter)
    }

    private fun subscribeUi() = with(viewModel) {
        personDetails.observe(viewLifecycleOwner) {
            setGlobalCreditData(it)
        }

        images.observe(viewLifecycleOwner) {
            setViewsVisibility(View.VISIBLE, binding.imagesTitle, binding.imageRecycler)
            posterImageAdapter.submitList(it)
        }

        movieCast.observe(viewLifecycleOwner) {
            setViewsVisibility(View.VISIBLE, binding.movieCastTitle, binding.movieCastRecycler)
            movieCastAdapter.submitList(it)
        }

        tvCast.observe(viewLifecycleOwner) {
            setViewsVisibility(View.VISIBLE, binding.tvCastTitle, binding.tvCastRecycler)
            tvCastAdapter.submitList(it)
        }
    }

    private fun setGlobalCreditData(person: PersonUiModel) = with(binding) {
        name.text = person.name
        description.text = person.biography
        birthdayDate.text = person.birthday.toSimpleDate()
        userRatingValue.text = person.popularity.toString()
        placeOfBirthText.text = person.placeOfBirth
        image.loadPosterImage(person.profilePath)

        ifNotEmpty(person.placeOfBirth) {
            setViewsVisibility(View.VISIBLE, placeOfBirth, placeOfBirthText)
            placeOfBirthText.text = person.placeOfBirth
        }

        person.deathday?.let {
            setViewsVisibility(View.VISIBLE, deathday, deathdayDate)
            deathdayDate.text = it.toSimpleDate()
        }
    }
}
