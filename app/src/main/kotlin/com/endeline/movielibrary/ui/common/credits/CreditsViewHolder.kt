package com.endeline.movielibrary.ui.common.credits

import androidx.recyclerview.widget.RecyclerView
import com.endeline.movielibrary.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.databinding.CreditItemBinding
import com.endeline.movielibrary.ui.extensions.loadBackdropImage

class CreditsViewHolder(val binding: CreditItemBinding, val listener: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonUiModel) = with(binding) {
        image.loadBackdropImage(item.profilePath)
        root.setOnClickListener {
            listener(item.id)
        }
    }
}
