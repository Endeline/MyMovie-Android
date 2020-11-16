package com.endeline.mymovie.ui.gui.details

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel
import com.endeline.mymovie.databinding.VideoLinkBinding
import com.endeline.mymovie.extensions.ifLet

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
