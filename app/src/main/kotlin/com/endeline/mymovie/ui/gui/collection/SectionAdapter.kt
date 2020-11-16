package com.endeline.mymovie.ui.gui.collection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.mymovie.databinding.SectionItemBinding

class SectionAdapter(
    private val viewHolderImageHeight: Int = 200,
    private val viewHolderImageWidth: Int = 150,
    private val clickListener: (Int) -> Unit
) : ListAdapter<ProductUiModel, SectionViewHolder>(MovieDiffer()) {

    private class MovieDiffer : DiffUtil.ItemCallback<ProductUiModel>() {
        override fun areItemsTheSame(
            oldItem: ProductUiModel,
            newItem: ProductUiModel
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ProductUiModel,
            newItem: ProductUiModel
        ) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SectionViewHolder(
            SectionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener,
            viewHolderImageHeight,
            viewHolderImageWidth
        )

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
