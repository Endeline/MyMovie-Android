package com.endeline.movielibrary.ui.gui.details.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.endeline.movielibrary.databinding.TvFragmentBinding
import com.endeline.movielibrary.di.ViewModelFactory
import com.endeline.movielibrary.di.components.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

class TvFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.TvViewModelFactory

    private val viewModel by viewModels<TvViewModel> {
        viewModelFactory
    }

    private val args by navArgs<TvFragmentArgs>()

    private var _binding: TvFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerAppComponent.create().inject(this)

        _binding = TvFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponent()
        subscribeUi()
        //viewModel.loadData()

        Timber.d("Wazne tv id ${args.tvId}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setComponent() {

    }

    private fun subscribeUi() {

    }
}
