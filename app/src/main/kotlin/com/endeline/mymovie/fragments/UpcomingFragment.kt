package com.endeline.mymovie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.UpcomingFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.viewmodels.UpcomingViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UpcomingFragment : Fragment() {

    @Inject
    protected lateinit var viewModel: UpcomingViewModel

    private lateinit var binding: UpcomingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UpcomingFragmentBinding.inflate(inflater)

        DaggerViewModelComponent.builder().build().inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUpcoming()
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d("$it")
            }, Timber::e)
    }

}
