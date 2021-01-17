package com.endeline.movielibrary.ui.gui.collection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.databinding.SectionItemBinding
import com.endeline.movielibrary.extensions.ifNotEmpty
import com.endeline.movielibrary.extensions.loadPosterImage
import com.endeline.movielibrary.extensions.px

class SectionViewHolder(
    private val binding: SectionItemBinding,
    private val clickListener: (Int) -> Unit,
    private val viewHolderImageHeight: Int,
    private val viewHolderImageWidth: Int
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductUiModel) = with(binding) {
        title.text = if (product.title.isNotEmpty()) {
            product.title
        } else {
            product.name
        }

        ifNotEmpty(product.overview) {
            description.apply {
                text = it
                visibility = View.VISIBLE
            }
        }

        imageView.apply {
            layoutParams.height = viewHolderImageHeight.px()
            layoutParams.width = viewHolderImageWidth.px()
            loadPosterImage(product.posterPath)
        }

        itemView.setOnClickListener {
            clickListener(product.id)
        }
    }
}
