package com.endeline.mymovie.fragments.nowplaying

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.mymovie.databinding.NowPlayingFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.viewmodels.NowPlayingViewModel
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class NowPlayingFragment : Fragment() {

    @Inject
    protected lateinit var viewModel: NowPlayingViewModel

    private lateinit var binding: NowPlayingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NowPlayingFragmentBinding.inflate(inflater)

        DaggerViewModelComponent.builder().build().inject(this)

        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNowPlaying()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    binding.recycleView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = NowPlayingAdapter(it.results)
                    }
                }, Timber::e
            )
    }

}
