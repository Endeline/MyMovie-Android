package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.SimilarRecommendationMovieBinding
import com.endeline.mymovie.extensions.loadPosterImage
import com.endeline.mymovie.fragments.DetailsFragmentDirections

class SimilarRecommendationMovieAdapter(
    private val movieCollection: List<MovieCollectionItemUiModel>?
) : RecyclerView.Adapter<SimilarRecommendationMovieAdapter.MovieHolder>() {

    class MovieHolder(view: SimilarRecommendationMovieBinding) :
        RecyclerView.ViewHolder(view.root) {
        val image = view.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder(SimilarRecommendationMovieBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int =
        movieCollection?.size ?: 0

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movieUiModel = movieCollection!![position]

        with(holder) {
            movieUiModel.posterPath?.let {
                image.loadPosterImage(it)
            }

            itemView.setOnClickListener {
                it.findNavController().navigate(
                    DetailsFragmentDirections.toDetails(movieUiModel.id ?: -1)
                )
            }
        }
    }
}