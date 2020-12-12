package com.endeline.movielibrary.ui.common.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.PosterImageBinding

class PosterImageAdapter : ListAdapter<ImageUiModel, PosterImageViewHolder>(ImagePosterDiffer()) {

    var listener: (String) -> Unit = {}

    private class ImagePosterDiffer : DiffUtil.ItemCallback<ImageUiModel>() {
        override fun areItemsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel) =
            oldItem.filePath == newItem.filePath

        override fun areContentsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PosterImageViewHolder(
            PosterImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: PosterImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
