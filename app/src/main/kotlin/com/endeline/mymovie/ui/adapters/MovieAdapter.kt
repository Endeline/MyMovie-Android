package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.MovieItemBinding

class MovieAdapter(
    private val clickListener: (Int) -> Unit
) : ListAdapter<MovieCollectionItemUiModel, MovieItemHolder>(MovieDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieItemHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context)),
            clickListener
        )

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
