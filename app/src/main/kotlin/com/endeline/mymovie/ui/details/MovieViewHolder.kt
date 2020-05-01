package com.endeline.mymovie.ui.details

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.SimilarRecommendationMovieBinding
import com.endeline.mymovie.extensions.loadPosterImage

class MovieViewHolder(
    val binding: SimilarRecommendationMovieBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiModel: MovieCollectionItemUiModel) = with(binding) {
        uiModel.posterPath?.let {
            image.loadPosterImage(it)
        }

        itemView.setOnClickListener {
            onClick(uiModel.id ?: -1)
        }
    }
}
