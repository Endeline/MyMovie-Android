package com.endeline.mymovie.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.mymovie.databinding.MovieItemBinding
import com.endeline.mymovie.extensions.ifNotEmpty
import com.endeline.mymovie.extensions.loadPosterImage
import com.endeline.mymovie.extensions.px

class ProductViewHolder(
    private val binding: MovieItemBinding,
    private val clickListener: (Int) -> Unit,
    private val viewHolderImageHeight: Int,
    private val viewHolderImageWidth: Int
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductUiModel) = with(binding) {

        ifNotEmpty(product.title) {
            title.apply {
                text = it
                visibility = View.VISIBLE
            }
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
