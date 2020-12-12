package com.endeline.movielibrary.ui.gui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.ProductsUiModel.ProductUiModel
import com.endeline.movielibrary.databinding.PosterImageBinding

class MovieAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<ProductUiModel, MovieViewHolder>(MovieDiffer()) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            PosterImageBinding.inflate(LayoutInflater.from(parent.context)),
            onClick
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
