package com.endeline.mymovie.ui.common.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.mymovie.databinding.ReviewItemBinding

class ReviewsAdapter : ListAdapter<ReviewUiModel, ReviewsViewHolder>(ReviewDiffer()) {

    private class ReviewDiffer : DiffUtil.ItemCallback<ReviewUiModel>() {
        override fun areItemsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewsViewHolder(
            ReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) =
        holder.bind(getItem(position))
}
