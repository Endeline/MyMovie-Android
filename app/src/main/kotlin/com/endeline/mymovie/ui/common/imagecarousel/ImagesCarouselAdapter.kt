package com.endeline.mymovie.ui.common.imagecarousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.mymovie.databinding.ImageCarouselItemBinding

class ImagesCarouselAdapter : ListAdapter<ImageUiModel, ImageCarouselViewHolder>(ImageDiffer()) {

    private class ImageDiffer : DiffUtil.ItemCallback<ImageUiModel>() {
        override fun areItemsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel) =
            oldItem.filePath == newItem.filePath

        override fun areContentsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageCarouselViewHolder(ImageCarouselItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ImageCarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
