package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.SearchItemUiModel
import com.endeline.mymovie.databinding.SearchItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage
import com.endeline.mymovie.extensions.loadPosterImage
import com.endeline.mymovie.fragments.SearchFragmentDirections
import com.endeline.mymovie.viewmodels.SearchViewModel

class SearchAdapter(private val collection: List<SearchItemUiModel>) :
    RecyclerView.Adapter<SearchAdapter.ItemHolder>() {

    private var items: List<SearchItemUiModel> = mutableListOf<SearchItemUiModel>().apply {
        collection.forEach {
            if (SearchViewModel.MediaType.fromString(it.mediaType) == SearchViewModel.MediaType.person) {
                if (!it.profilePath.isNullOrBlank()) {
                    add(it)
                }
            } else if (!it.backdropPath.isNullOrBlank() && !it.posterPath.isNullOrBlank()) {
                add(it)
            }
        }
    }

    class ItemHolder(view: SearchItemBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemHolder(SearchItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() =
        items.size

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]


        if (SearchViewModel.MediaType.fromString(item.mediaType) == SearchViewModel.MediaType.person) {
            //todo on click on person create person details fragment
            item.profilePath?.let {
                holder.image.loadPosterImage(it)
            }
        } else {
            holder.itemView.setOnClickListener {
                it.findNavController().navigate(
                    SearchFragmentDirections.toDetails(item.id ?: -1))
            }

            if (!item.posterPath.isNullOrBlank()) {
                item.posterPath?.let {
                    holder.image.loadPosterImage(it)
                }
            } else {
                item.backdropPath?.let {
                    holder.image.loadLandscapeImage(it)
                }
            }
        }
    }
}