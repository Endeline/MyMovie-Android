package com.endeline.mymovie.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import com.endeline.mymovie.databinding.MovieItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage

class MovieItemHolder(
    private val binding: MovieItemBinding,
    private val clickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movieUiModel: MovieItemUiModel) = with(binding) {
        title.text = movieUiModel.title
        description.text = movieUiModel.overview
        imageView.loadLandscapeImage(movieUiModel.backdropPath)
        itemView.setOnClickListener {
            clickListener(movieUiModel.id)
        }
    }
}
