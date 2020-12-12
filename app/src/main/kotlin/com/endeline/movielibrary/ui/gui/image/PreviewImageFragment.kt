package com.endeline.movielibrary.ui.gui.image

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.PreviewImageFragmentBinding
import com.endeline.movielibrary.extensions.loadPosterImage
import com.endeline.movielibrary.extensions.px
import com.endeline.movielibrary.ui.Constants.DialogSize.WINDOW_HEIGHT
import com.endeline.movielibrary.ui.Constants.DialogSize.WINDOW_WIDTH

class PreviewImageFragment : DialogFragment() {

    private val args by navArgs<PreviewImageFragmentArgs>()

    private var _binding: PreviewImageFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PreviewImageFragmentBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialog()
        setComponent()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setDialog() {
        dialog?.let { dialog ->
            dialog.setCanceledOnTouchOutside(true)
            dialog.window?.let { window ->
                window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                window.attributes?.let { attrs ->
                    attrs.width = WINDOW_WIDTH.px()
                    attrs.height = WINDOW_HEIGHT.px()
                }
            }
        }
    }

    private fun setComponent() = with(binding) {
        image.loadPosterImage(args.imageUrl)
    }
}
