package com.endeline.movielibrary.ui.common.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.ImageCarouselItemBinding
import javax.inject.Inject

class ImagesCarouselAdapter @Inject constructor() : ListAdapter<ImageUiModel, ImageCarouselViewHolder>(ImageDiffer()) {

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
