package com.endeline.mymovie.ui.search

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage
import com.endeline.mymovie.extensions.loadPosterImage

class SearchItemViewHolder(
    val binding: SearchItemBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiModel: SearchItemUiModel) = with(binding) {
        if (MediaType.fromString(uiModel.mediaType) == MediaType.person) {
            //todo on click on person create person details fragment
            uiModel.profilePath?.let {
                image.loadPosterImage(it)
            }
        } else {
            image.setOnClickListener {
                onClick(uiModel.id ?: -1)
            }

            if (!uiModel.posterPath.isNullOrBlank()) {
                uiModel.posterPath?.let {
                    image.loadPosterImage(it)
                }
            } else {
                uiModel.backdropPath?.let {
                    image.loadLandscapeImage(it)
                }
            }
        }
    }
}
