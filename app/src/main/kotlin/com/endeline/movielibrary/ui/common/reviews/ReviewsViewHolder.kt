package com.endeline.movielibrary.ui.common.reviews

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.movielibrary.databinding.ReviewItemBinding

class ReviewsViewHolder(val binding: ReviewItemBinding, val listener: (ReviewUiModel) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReviewUiModel) = with(binding) {
        author.text = item.authorDetails.userName
        description.text = item.content

        root.setOnClickListener {
            listener(item)
        }
    }
}
