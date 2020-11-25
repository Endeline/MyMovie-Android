package com.endeline.mymovie.ui.common.credits

import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.CreditsUiModel
import com.endeline.mymovie.databinding.CreditItemBinding
import com.endeline.mymovie.extensions.loadBackdropImage

class CreditsViewHolder(val binding: CreditItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CreditsUiModel.PersonUiModel) = with(binding) {
        image.loadBackdropImage(item.profilePath)
        root.setOnClickListener {
            //TODO onClick PERSON
        }
    }
}
