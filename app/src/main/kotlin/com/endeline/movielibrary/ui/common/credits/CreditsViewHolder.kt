package com.endeline.movielibrary.ui.common.credits

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.NavigationGraphXmlDirections
import com.endeline.movielibrary.databinding.CreditItemBinding
import com.endeline.movielibrary.extensions.loadBackdropImage

class CreditsViewHolder(val binding: CreditItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonUiModel) = with(binding) {
        image.loadBackdropImage(item.profilePath)
        root.setOnClickListener {
            it.findNavController().navigate(NavigationGraphXmlDirections.navigateToPerson(item.id))
        }
    }
}
