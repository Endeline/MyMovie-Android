package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.mymovie.databinding.MovieItemBinding

class ProductAdapter(
    private val viewHolderImageHeight: Int = 200,
    private val viewHolderImageWidth: Int = 150,
    private val clickListener: (Int) -> Unit
) : ListAdapter<ProductUiModel, ProductViewHolder>(MovieDiffer()) {

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
        ProductViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener,
            viewHolderImageHeight,
            viewHolderImageWidth
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
