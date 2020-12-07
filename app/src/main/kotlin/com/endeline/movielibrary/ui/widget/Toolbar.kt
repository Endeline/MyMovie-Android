package com.endeline.movielibrary.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.endeline.movielibrary.NavigationGraphXmlDirections
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.ToolbarBinding

//TODO create one toolbar for all app and change visibility item of live data from sharedViewModel
class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : Toolbar(context, attrs, androidx.appcompat.R.attr.toolbarStyle) {

    private val binding = ToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val shouldShowBackIcon: Boolean
        val shouldShowSearchIcon: Boolean
        val array = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.toolbar,
            androidx.appcompat.R.attr.toolbarStyle,
            0
        )

        try {
            shouldShowBackIcon = array.getBoolean(R.styleable.toolbar_showBackIcon, false)
            shouldShowSearchIcon = array.getBoolean(R.styleable.toolbar_showSearchIcon, true)
        } finally {
            array.recycle()
        }

        setVisibility(binding.icBack, shouldShowBackIcon)
        setVisibility(binding.icSearch, shouldShowSearchIcon)

        binding.icSearch.setOnClickListener {
            findNavController().navigate(NavigationGraphXmlDirections.navigateToSearch())
        }

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setVisibility(view: View, shouldShow: Boolean) {
        view.visibility = if (shouldShow) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
