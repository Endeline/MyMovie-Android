package com.endeline.movielibrary.ui.common.video

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.movielibrary.databinding.VideoLinkBinding
import com.endeline.movielibrary.ui.extensions.ifLet

class VideoViewHolder(
    val binding: VideoLinkBinding,
    val clickListener: (String, String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: VideoLinkDetailsUiModel) = with(binding) {
        title.text = item.name

        itemView.setOnClickListener {
            ifLet(item.key, item.site) { (key, site) ->
                clickListener(key, site)
            }
        }
    }
}
