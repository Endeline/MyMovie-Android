package com.endeline.movielibrary.ui.common.reviews

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.movielibrary.NavigationGraphXmlDirections
import com.endeline.movielibrary.databinding.ReviewItemBinding

class ReviewsViewHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReviewUiModel) = with(binding) {
        author.text = item.authorDetails.userName
        description.text = item.content

        root.setOnClickListener {
            root.findNavController().navigate(NavigationGraphXmlDirections.navigateToReview(
                item.authorDetails.userName,
                item.content,
                item.authorDetails.avatarPath,
                item.authorDetails.rating
            ))
        }
    }
}
