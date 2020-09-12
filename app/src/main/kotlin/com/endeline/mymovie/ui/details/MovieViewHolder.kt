package com.endeline.mymovie.ui.details

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.mymovie.databinding.SimilarRecommendationMovieBinding
import com.endeline.mymovie.extensions.loadPosterImage

class MovieViewHolder(
    val binding: SimilarRecommendationMovieBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MovieItemUiModel) = with(binding) {
        image.loadPosterImage(item.posterPath)
        itemView.setOnClickListener {
            onClick(item.id)
        }
    }
}
