package com.endeline.movielibrary.ui.common.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.movielibrary.databinding.VideoLinkBinding
import javax.inject.Inject

class VideoAdapter @Inject constructor() : ListAdapter<VideoLinkDetailsUiModel, VideoViewHolder>(VideoDiffer()) {

    private class VideoDiffer : DiffUtil.ItemCallback<VideoLinkDetailsUiModel>() {
        override fun areItemsTheSame(
            oldItem: VideoLinkDetailsUiModel,
            newItem: VideoLinkDetailsUiModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: VideoLinkDetailsUiModel,
            newItem: VideoLinkDetailsUiModel
        ) = oldItem == newItem
    }

    var listener: (String, String) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(
            VideoLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
