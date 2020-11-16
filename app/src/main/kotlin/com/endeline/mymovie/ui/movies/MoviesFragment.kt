package com.endeline.mymovie.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.endeline.mymovie.databinding.ProductListFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class MoviesFragment : Fragment() {

    private var _binding: ProductListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProductListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setComponent() = with(binding) {
        val adapter = PagerAdapter(this@MoviesFragment)

        pager.adapter = adapter

        TabLayoutMediator(tabsLayout, pager) { tab, position ->
            tab.text = adapter.getNamePosition(position)
        }.attach()
    }
}