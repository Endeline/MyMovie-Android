package com.endeline.mymovie.ui.search

import androidx.recyclerview.widget.DiffUtil
import com.endeline.domain.uimodels.SearchItemUiModel

class SearchDiffer : DiffUtil.ItemCallback<SearchItemUiModel>() {
    override fun areItemsTheSame(oldItem: SearchItemUiModel, newItem: SearchItemUiModel) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchItemUiModel, newItem: SearchItemUiModel) =
        oldItem == newItem
}