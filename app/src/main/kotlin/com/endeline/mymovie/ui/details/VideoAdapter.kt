package com.endeline.mymovie.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.VideoLinkDetailsUiModel
import com.endeline.mymovie.databinding.VideoLinkBinding

class VideoAdapter(private val clickListener: (String, String) -> Unit) :
    ListAdapter<VideoLinkDetailsUiModel, VideoViewHolder>(VideoDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(
            VideoLinkBinding.inflate(LayoutInflater.from(parent.context)),
            clickListener
        )

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
