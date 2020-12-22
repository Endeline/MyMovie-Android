package com.endeline.movielibrary.ui.gui.details.movie

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.databinding.PosterImageBinding
import com.endeline.movielibrary.extensions.loadPosterImage

class MovieViewHolder(
    val binding: PosterImageBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProductUiModel) = with(binding) {
        image.loadPosterImage(item.posterPath)
        itemView.setOnClickListener {
            onClick(item.id)
        }
    }
}
