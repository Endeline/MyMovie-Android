package com.endeline.movielibrary.ui.gui.review

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.ReviewFragmentBinding
import com.endeline.movielibrary.ui.extensions.ifNotEmpty
import com.endeline.movielibrary.ui.extensions.loadImage
import com.endeline.movielibrary.ui.extensions.px
import com.endeline.movielibrary.Constants.Size.WINDOW_HEIGHT
import com.endeline.movielibrary.Constants.Size.WINDOW_WIDTH
import dagger.android.support.DaggerDialogFragment

class ReviewFragment : DaggerDialogFragment() {

    private val args by navArgs<ReviewFragmentArgs>()

    private var _binding: ReviewFragmentBinding? = null
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
        _binding = ReviewFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDialog()
        setComponent()
    }

    override fun onDestroyView() {
        super.onDestroyView()
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
        ifNotEmpty(args.avatarPath) {
            image.loadImage(it)
        }

        if (args.rating > 0) {
            rating.text = getString(R.string.user_rating, args.rating)
        }

        author.text = args.author
        description.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(args.content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(args.content)
        }
    }
}
