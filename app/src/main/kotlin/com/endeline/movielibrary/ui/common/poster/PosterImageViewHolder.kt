package com.endeline.movielibrary.ui.common.poster

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.PosterImageBinding
import com.endeline.movielibrary.ui.extensions.loadPosterImage

class PosterImageViewHolder(val binding: PosterImageBinding, val listener: (String) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageUiModel) = with(binding) {
        image.apply {
            loadPosterImage(item.filePath)
            setOnClickListener {
                listener(item.filePath)
            }
        }
    }
}
