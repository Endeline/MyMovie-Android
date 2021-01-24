package com.endeline.movielibrary.ui.common.carousel

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.ImageCarouselItemBinding
import com.endeline.movielibrary.ui.extensions.loadBackdropImage

class ImageCarouselViewHolder(val binding: ImageCarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageUiModel) {
        binding.image.loadBackdropImage(item.filePath)
    }
}
