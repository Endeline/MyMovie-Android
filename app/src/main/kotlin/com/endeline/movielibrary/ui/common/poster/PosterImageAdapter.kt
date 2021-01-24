package com.endeline.movielibrary.ui.common.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.movielibrary.domain.uimodels.ImagesUiModel.ImageUiModel
import com.endeline.movielibrary.databinding.PosterImageBinding
import javax.inject.Inject

class PosterImageAdapter @Inject constructor() : ListAdapter<ImageUiModel, PosterImageViewHolder>(ImagePosterDiffer()) {

    private class ImagePosterDiffer : DiffUtil.ItemCallback<ImageUiModel>() {
        override fun areItemsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel) =
            oldItem.filePath == newItem.filePath

        override fun areContentsTheSame(oldItem: ImageUiModel, newItem: ImageUiModel) =
            oldItem == newItem
    }

    var listener: (String) -> Unit = {}

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
