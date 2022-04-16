package com.santimattius.template.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.santimattius.template.core.presentation.load
import com.santimattius.template.databinding.ItemMovieBinding
import com.santimattius.template.ui.models.MovieUiModel

class MovieViewHolder(private val viewBinding: ItemMovieBinding) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(item: MovieUiModel, onItemClick: (MovieUiModel) -> Unit = {}) {
        with(viewBinding) {
            imageMovie.load(item.imageUrl)
            itemRootContainer.setOnClickListener { onItemClick(item) }
        }
    }

    companion object {

        fun from(parent: ViewGroup): MovieViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val viewBinding = ItemMovieBinding.inflate(
                inflater,
                parent,
                false
            )
            return MovieViewHolder(viewBinding)
        }
    }
}
