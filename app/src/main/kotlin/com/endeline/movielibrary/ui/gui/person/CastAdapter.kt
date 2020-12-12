package com.endeline.movielibrary.ui.gui.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.databinding.CastItemBinding

class CastAdapter : ListAdapter<ProductUiModel, CastViewHolder>(CastDiffer()) {

    private class CastDiffer : DiffUtil.ItemCallback<ProductUiModel>() {
        override fun areItemsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProductUiModel, newItem: ProductUiModel) =
            oldItem == newItem
    }

    var listener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CastViewHolder(
            CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
