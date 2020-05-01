package com.endeline.mymovie.ui.details

import androidx.recyclerview.widget.DiffUtil
import com.endeline.domain.uimodels.MovieCollectionItemUiModel

class MovieDiffer : DiffUtil.ItemCallback<MovieCollectionItemUiModel>() {
    override fun areItemsTheSame(
        oldItem: MovieCollectionItemUiModel,
        newItem: MovieCollectionItemUiModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: MovieCollectionItemUiModel,
        newItem: MovieCollectionItemUiModel
    ) = oldItem == newItem
}
