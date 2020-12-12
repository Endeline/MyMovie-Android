package com.endeline.movielibrary.ui.gui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.movielibrary.databinding.VideoLinkBinding

class VideoAdapter(private val clickListener: (String, String) -> Unit) :
    ListAdapter<VideoLinkDetailsUiModel, VideoViewHolder>(VideoDiffer()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(
            VideoLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}