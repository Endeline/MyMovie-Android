package com.endeline.movielibrary.ui.common.imagecarousel

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.ImageCarouselItemBinding
import com.endeline.movielibrary.extensions.loadBackdropImage

class ImageCarouselViewHolder(val binding: ImageCarouselItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ImageUiModel) {
        binding.image.loadBackdropImage(item.filePath)
    }
}
