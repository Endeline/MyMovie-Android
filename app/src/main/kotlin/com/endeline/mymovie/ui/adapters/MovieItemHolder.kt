package com.endeline.mymovie.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.MovieItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage

class MovieItemHolder(
    private val binding: MovieItemBinding,
    private val clickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieUiModel: MovieCollectionItemUiModel) = with(binding) {
        title.text = movieUiModel.title
        description.text = movieUiModel.overview

        movieUiModel.backdropPath?.let {
            imageView.loadLandscapeImage(it)
        }

        itemView.setOnClickListener {
            clickListener(movieUiModel.id ?: -1)
        }
    }
}
