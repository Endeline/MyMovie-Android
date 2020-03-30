package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.MovieItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage
import com.endeline.mymovie.fragments.NowPlayingFragmentDirections

class MovieAdapter(
    private val movieCollection: List<MovieCollectionItemUiModel>?
) : RecyclerView.Adapter<MovieAdapter.MovieItemHolder>() {

    class MovieItemHolder(view: MovieItemBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.imageView
        val title = view.title
        val description = view.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder =
        MovieItemHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    override fun getItemCount(): Int =
        movieCollection?.size ?: 0

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        val movieUiModel = movieCollection!![position]

        with(holder) {
            title.text = movieUiModel.title
            description.text = movieUiModel.overview

            movieUiModel.backdropPath?.let {
                image.loadLandscapeImage(it)
            }

            itemView.setOnClickListener {
                it.findNavController().navigate(
                    NowPlayingFragmentDirections.toDetails(
                        movieUiModel.id ?: -1
                    )
                )
            }
        }
    }

}
