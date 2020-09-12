package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.mymovie.databinding.MovieItemBinding

class MovieAdapter(
    private val clickListener: (Int) -> Unit
) : ListAdapter<MovieItemUiModel, MovieItemHolder>(MovieDiffer()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieItemHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
