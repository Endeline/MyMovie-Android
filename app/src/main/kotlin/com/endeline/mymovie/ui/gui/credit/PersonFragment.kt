package com.endeline.mymovie.ui.gui.credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.mymovie.databinding.PersonFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.di.components.DaggerAppComponent
import com.endeline.mymovie.extensions.loadPosterImage
import com.endeline.mymovie.extensions.setViewsVisibility
import com.endeline.mymovie.extensions.toSimpleDate
import javax.inject.Inject

class PersonFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.PersonViewModelFactory

    private val viewModel by viewModels<PersonViewModel> {
        viewModelFactory
    }

    private val args by navArgs<PersonFragmentArgs>()

    private var _binding: PersonFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerAppComponent.create().inject(this)
        _binding = PersonFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)

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

    private fun setComponents() {

    }

    private fun subscribeUi() = with(viewModel) {
        personDetails.observe(viewLifecycleOwner) {
            setGlobalCreditData(it)
        }
    }

    private fun setGlobalCreditData(person: PersonUiModel) = with(binding) {
        name.text = person.name
        description.text = person.biography
        birthdayDate.text = person.birthday.toSimpleDate()
        placeOfBirthText.text = person.placeOfBirth
        userRatingValue.text = person.popularity.toString()
        image.loadPosterImage(person.profilePath)
        person.deathday?.let {
            setViewsVisibility(View.VISIBLE, deathday, deathdayDate)
            deathdayDate.text = it.toSimpleDate()
        }
    }
}
