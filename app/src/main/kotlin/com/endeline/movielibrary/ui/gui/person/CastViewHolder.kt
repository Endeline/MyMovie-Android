package com.endeline.movielibrary.ui.gui.person

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.databinding.CastItemBinding
import com.endeline.movielibrary.ui.extensions.loadPosterImage

class CastViewHolder(val binding: CastItemBinding, val listener: (Int) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProductUiModel) = with(binding) {
        image.loadPosterImage(item.backdropPath)
        description.text = item.character
        root.setOnClickListener {
            listener(item.id)
        }
    }
}
