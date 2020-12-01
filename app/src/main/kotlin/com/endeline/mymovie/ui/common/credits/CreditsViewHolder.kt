package com.endeline.mymovie.ui.common.credits

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.mymovie.NavigationGraphXmlDirections
import com.endeline.mymovie.databinding.CreditItemBinding
import com.endeline.mymovie.extensions.loadBackdropImage

class CreditsViewHolder(val binding: CreditItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonUiModel) = with(binding) {
        image.loadBackdropImage(item.profilePath)
        root.setOnClickListener {
            it.findNavController().navigate(NavigationGraphXmlDirections.navigateToPerson(item.id))
        }
    }
}
