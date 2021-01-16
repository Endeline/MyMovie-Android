package com.endeline.movielibrary.ui.gui.details.tv

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.endeline.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.Constants.Collections.MINIMUM_COLLECTION_SIZE
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.SeasonItemBinding
import com.endeline.movielibrary.extensions.ifNotEmpty
import com.endeline.movielibrary.extensions.loadPosterImage

class SeasonViewHolder(
    private val binding: SeasonItemBinding,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ProductDetailsUiModel.SeasonUiModel) = with(binding) {
        root.setOnClickListener {
            onClickListener(item.id)
        }

        ifNotEmpty(item.overview) { overview ->
            setText(description, overview)
        }

        ifNotEmpty(item.name) { name ->
            setText(title, name)
        }

        if (item.episodeCount >= MINIMUM_COLLECTION_SIZE) {
            setText(episodes, root.context.getString(R.string.episodes, item.episodeCount))
        }

        image.loadPosterImage(item.posterPath)
    }

    private fun setText(textView: AppCompatTextView, textToSet: String) = textView.apply {
        visibility = View.VISIBLE
        text = textToSet
    }
}
