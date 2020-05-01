package com.endeline.mymovie.ui.details

import androidx.recyclerview.widget.DiffUtil
import com.endeline.domain.uimodels.VideoLinkDetailsUiModel

class VideoDiffer : DiffUtil.ItemCallback<VideoLinkDetailsUiModel>() {
    override fun areItemsTheSame(
        oldItem: VideoLinkDetailsUiModel,
        newItem: VideoLinkDetailsUiModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: VideoLinkDetailsUiModel,
        newItem: VideoLinkDetailsUiModel
    ) = oldItem == newItem

}
