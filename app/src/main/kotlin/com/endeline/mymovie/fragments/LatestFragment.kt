package com.endeline.mymovie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.LatestFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.viewmodels.LatestViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class LatestFragment : Fragment() {

    @Inject
    protected lateinit var viewModel: LatestViewModel

    private lateinit var binding: LatestFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LatestFragmentBinding.inflate(inflater)

        DaggerViewModelComponent.builder().build().inject(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLatest()
            .subscribeOn(Schedulers.io())
            .subscribe({
                    Timber.d("$it")
                }, Timber::e
            )
    }

}
