package com.endeline.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.VideoLinkDetailsUiModel
import com.endeline.mymovie.databinding.DetailsFragmentBinding
import com.endeline.mymovie.databinding.VideoLinkBinding
import com.endeline.mymovie.fragments.DetailsFragmentDirections

class VideoAdapter(private val videoLinks: List<VideoLinkDetailsUiModel>?) :
    RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    class VideoHolder(view: VideoLinkBinding) : RecyclerView.ViewHolder(view.root) {
        val title = view.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoHolder(VideoLinkBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() =
        videoLinks?.size ?: 0

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        val link = videoLinks!![position]

        with(holder) {
            title.text = link.name
            itemView.setOnClickListener {
                it.findNavController().navigate(
                    DetailsFragmentDirections.toVideo(link.key ?: "")
                )
            }
        }
    }

}
