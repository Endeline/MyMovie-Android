package com.endeline.movielibrary.ui.common.movie

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.databinding.PosterImageBinding
import com.endeline.movielibrary.ui.extensions.loadPosterImage

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
