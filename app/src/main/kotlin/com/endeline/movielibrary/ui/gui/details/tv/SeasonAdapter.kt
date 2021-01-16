package com.endeline.movielibrary.ui.gui.details.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ProductDetailsUiModel.SeasonUiModel
import com.endeline.movielibrary.databinding.SeasonItemBinding

class SeasonAdapter : ListAdapter<SeasonUiModel, SeasonViewHolder>(SeasonDiffer()) {

    private class SeasonDiffer : DiffUtil.ItemCallback<SeasonUiModel>() {
        override fun areItemsTheSame(
            oldItem: SeasonUiModel,
            newItem: SeasonUiModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: SeasonUiModel,
            newItem: SeasonUiModel
        ) = oldItem == newItem

    }

    var listener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SeasonViewHolder(
        SeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        listener
    )

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
