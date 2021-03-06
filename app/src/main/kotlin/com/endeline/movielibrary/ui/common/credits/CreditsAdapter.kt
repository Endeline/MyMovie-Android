package com.endeline.movielibrary.ui.common.credits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.movielibrary.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.databinding.CreditItemBinding

class CreditsAdapter : ListAdapter<PersonUiModel, CreditsViewHolder>(CreditsDiffer()) {

    private class CreditsDiffer : DiffUtil.ItemCallback<PersonUiModel>() {
        override fun areItemsTheSame(oldItem: PersonUiModel, newItem: PersonUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PersonUiModel, newItem: PersonUiModel) =
            oldItem == newItem
    }

    var listener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CreditsViewHolder(
            CreditItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) =
        holder.bind(getItem(position))
}
