package com.endeline.mymovie.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.SearchAllUiModel.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchItemBinding

class SearchAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<SearchItemUiModel, SearchItemViewHolder>(SearchDiffer()) {

    private class SearchDiffer : DiffUtil.ItemCallback<SearchItemUiModel>() {
        override fun areItemsTheSame(oldItem: SearchItemUiModel, newItem: SearchItemUiModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: SearchItemUiModel, newItem: SearchItemUiModel) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchItemViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context)),
            onClick
        )

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
