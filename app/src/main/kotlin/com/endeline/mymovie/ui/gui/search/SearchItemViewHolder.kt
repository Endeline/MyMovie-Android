package com.endeline.mymovie.ui.gui.search

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.SearchUiModel.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchItemBinding
import com.endeline.mymovie.extensions.loadBackdropImage
import com.endeline.mymovie.extensions.loadPosterImage

class SearchItemViewHolder(
    val binding: SearchItemBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    
    fun bind(item: SearchItemUiModel) = with(binding) {
        image.setOnClickListener {
            onClick(item.id)
        }

        when (MediaType.fromString(item.mediaType)) {
            MediaType.person -> image.loadPosterImage(item.profilePath)
            MediaType.movie,
            MediaType.tv -> if (item.posterPath.isNotBlank()) {
                image.loadPosterImage(item.posterPath)
            } else {
                image.loadBackdropImage(item.backdropPath)
            }
        }
    }
}
