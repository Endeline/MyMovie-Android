package com.endeline.mymovie.ui.gui.details

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.mymovie.databinding.SimilarRecommendationMovieBinding
import com.endeline.mymovie.extensions.loadPosterImage

class MovieViewHolder(
    val binding: SimilarRecommendationMovieBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProductUiModel) = with(binding) {
        image.loadPosterImage(item.posterPath)
        itemView.setOnClickListener {
            onClick(item.id)
        }
    }
}
