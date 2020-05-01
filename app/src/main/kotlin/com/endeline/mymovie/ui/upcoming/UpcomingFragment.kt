package com.endeline.mymovie.ui.upcoming

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.endeline.mymovie.databinding.MovieFragmentBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.ui.adapters.MovieAdapter
import javax.inject.Inject

class UpcomingFragment : Fragment() {

    @Inject
    protected lateinit var viewModel: UpcomingViewModel

    private lateinit var binding: MovieFragmentBinding

    private val movieAdapter = MovieAdapter(clickListener = {
        findNavController().navigate(UpcomingFragmentDirections.toDetails(it))
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieFragmentBinding.inflate(inflater)

        DaggerViewModelComponent.builder().build().inject(this)

        binding.recycleView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movieAdapter
        }

        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            upcomingLiveData.observe(viewLifecycleOwner, Observer {
                movieAdapter.submitList(it)
            })

            loadUpcoming()
        }
    }

}
