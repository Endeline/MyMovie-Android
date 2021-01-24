package com.endeline.movielibrary.ui.common.reviews

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.movielibrary.domain.uimodels.ReviewsUiModel.ReviewUiModel
import com.endeline.movielibrary.databinding.ReviewItemBinding
import javax.inject.Inject

class ReviewsAdapter @Inject constructor() : ListAdapter<ReviewUiModel, ReviewsViewHolder>(ReviewDiffer()) {

    private class ReviewDiffer : DiffUtil.ItemCallback<ReviewUiModel>() {
        override fun areItemsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ReviewUiModel, newItem: ReviewUiModel) =
            oldItem == newItem
    }

    var listener: (ReviewUiModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewsViewHolder(
            ReviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) =
        holder.bind(getItem(position))
}
