package com.endeline.mymovie.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.SimilarRecommendationMovieBinding
import com.endeline.mymovie.extensions.loadPosterImage

class MovieAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<MovieCollectionItemUiModel, MovieViewHolder>(MovieDiffer()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            SimilarRecommendationMovieBinding.inflate(LayoutInflater.from(parent.context)),
            onClick
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}