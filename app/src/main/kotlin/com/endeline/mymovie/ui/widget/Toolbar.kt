package com.endeline.mymovie.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.endeline.mymovie.NavigationGraphXmlDirections
import com.endeline.mymovie.databinding.ToolbarBinding

class Toolbar @JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null
) : Toolbar(context, attrs, androidx.appcompat.R.attr.toolbarStyle) {

    private val binding =  ToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.icSearch.setOnClickListener {
            findNavController().navigate(NavigationGraphXmlDirections.navigateToSearch())
        }

        binding.icProfile.setOnClickListener {
            findNavController().navigate(NavigationGraphXmlDirections.navigateToUser())
        }
    }

}