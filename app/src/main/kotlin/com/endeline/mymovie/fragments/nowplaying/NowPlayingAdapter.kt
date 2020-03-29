package com.endeline.mymovie.fragments.nowplaying

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.MovieCollectionItemUiModel
import com.endeline.mymovie.databinding.NowPlayingItemBinding
import com.endeline.mymovie.extensions.loadLandscapeImage

class NowPlayingAdapter(
    private val movieCollection: List<MovieCollectionItemUiModel>?
) : RecyclerView.Adapter<NowPlayingAdapter.FavoriteItemHolder>() {

    class FavoriteItemHolder(view: NowPlayingItemBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.imageView
        val title = view.title
        val description = view.description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteItemHolder =
        FavoriteItemHolder(NowPlayingItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount(): Int =
        movieCollection?.size ?: 0

    override fun onBindViewHolder(holder: FavoriteItemHolder, position: Int) {
        val movieUiModel = movieCollection!![position]

        with(holder) {
            title.text = movieUiModel.title
            description.text = movieUiModel.overview

            movieUiModel.backdropPath?.let {
                image.loadLandscapeImage(it)
            }

            itemView.setOnClickListener {
                it.findNavController().navigate(
                    NowPlayingFragmentDirections.toDetails(movieUiModel.id ?: -1)
                )
            }
        }
    }

}
