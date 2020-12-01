package com.endeline.mymovie.ui.gui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.endeline.mymovie.databinding.CollectionFragmentBinding
import com.google.android.material.tabs.TabLayoutMediator

class CollectionFragment : Fragment() {

    private var _binding: CollectionFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<CollectionFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CollectionFragmentBinding.inflate(inflater, container, false)

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
        val adapter = PagerAdapter(this@CollectionFragment, args.type)

        pager.adapter = adapter

        TabLayoutMediator(tabsLayout, pager) { tab, position ->
            tab.text = adapter.getNamePosition(position)
        }.attach()
    }
}