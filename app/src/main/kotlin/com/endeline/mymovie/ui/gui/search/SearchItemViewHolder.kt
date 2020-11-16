package com.endeline.mymovie.ui.gui.search

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.SearchAllUiModel.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage
import com.endeline.mymovie.extensions.loadPosterImage

class SearchItemViewHolder(
    val binding: SearchItemBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    //todo fix it
    fun bind(item: SearchItemUiModel) = with(binding) {
        when (MediaType.fromString(item.mediaType)) {
            MediaType.person -> {
                image.loadPosterImage(item.profilePath)
            }
            MediaType.movie,
            MediaType.tv -> {
                image.setOnClickListener {
                    onClick(item.id)
                }

                if (!item.posterPath.isBlank()) {
                    image.loadPosterImage(item.posterPath)
                } else {
                    image.loadLandscapeImage(item.backdropPath)
                }
            }
        }
    }
}
