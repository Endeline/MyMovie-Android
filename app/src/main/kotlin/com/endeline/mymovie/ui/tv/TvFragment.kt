package com.endeline.mymovie.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.endeline.mymovie.databinding.ProductListFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class TvFragment : Fragment() {

    private lateinit var binding: ProductListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProductListFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponent()
    }

    private fun setComponent() = with(binding) {
        val adapter = PagerAdapter(this@TvFragment)

        pager.adapter = adapter

        TabLayoutMediator(tabsLayout, pager) { tab, position ->
            tab.text = adapter.getNamePosition(position)
        }.attach()
    }
}