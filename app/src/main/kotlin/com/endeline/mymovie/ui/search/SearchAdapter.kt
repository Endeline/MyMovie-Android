package com.endeline.mymovie.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage
import com.endeline.mymovie.extensions.loadPosterImage

class SearchAdapter(private val onClick: (Int) -> Unit) :
    ListAdapter<SearchItemUiModel, SearchItemViewHolder>(SearchDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchItemViewHolder(
            SearchItemBinding.inflate(LayoutInflater.from(parent.context)),
            onClick
        )

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
