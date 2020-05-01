package com.endeline.mymovie.ui.details

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.VideoLinkDetailsUiModel
import com.endeline.mymovie.databinding.VideoLinkBinding

class VideoViewHolder(
    val binding: VideoLinkBinding,
    val clickListener: (String, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(uiModel: VideoLinkDetailsUiModel) = with(binding) {
        title.text = uiModel.name

        itemView.setOnClickListener {
            clickListener(uiModel.key ?: "", uiModel.site ?: "")
        }
    }
}
