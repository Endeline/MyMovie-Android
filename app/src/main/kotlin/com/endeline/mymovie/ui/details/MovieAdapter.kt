package com.endeline.mymovie.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.mymovie.databinding.SimilarRecommendationMovieBinding

class MovieAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<MovieItemUiModel, MovieViewHolder>(MovieDiffer()) {

    private class MovieDiffer : DiffUtil.ItemCallback<MovieItemUiModel>() {
        override fun areItemsTheSame(
            oldItem: MovieItemUiModel,
            newItem: MovieItemUiModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: MovieItemUiModel,
            newItem: MovieItemUiModel
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            SimilarRecommendationMovieBinding.inflate(LayoutInflater.from(parent.context)),
            onClick
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
