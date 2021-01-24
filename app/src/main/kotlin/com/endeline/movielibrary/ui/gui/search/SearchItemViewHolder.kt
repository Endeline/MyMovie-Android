package com.endeline.movielibrary.ui.gui.search

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.domain.uimodels.SearchUiModel.SearchItemUiModel
import com.endeline.movielibrary.databinding.SearchItemBinding
import com.endeline.movielibrary.ui.extensions.loadBackdropImage
import com.endeline.movielibrary.ui.extensions.loadPosterImage
import com.endeline.movielibrary.Constants.String.UNSUPPORTED_TYPE
import java.lang.RuntimeException

class SearchItemViewHolder(
    val binding: SearchItemBinding,
    val onClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    
    fun bind(item: SearchItemUiModel) = with(binding) {
        image.setOnClickListener {
            onClick(item.id)
        }

        when (ProductType.fromString(item.mediaType)) {
            ProductType.PERSON -> image.loadPosterImage(item.profilePath)
            ProductType.MOVIE,
            ProductType.TV -> if (item.posterPath.isNotBlank()) {
                image.loadPosterImage(item.posterPath)
            } else {
                image.loadBackdropImage(item.backdropPath)
            }
            else -> throw RuntimeException(UNSUPPORTED_TYPE)

        }
    }
}
