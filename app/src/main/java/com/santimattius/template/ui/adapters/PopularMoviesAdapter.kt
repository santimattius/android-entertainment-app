package com.santimattius.template.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.santimattius.template.ui.adapters.diff.MovieDiffItem
import com.santimattius.template.ui.adapters.viewholders.MovieViewHolder
import com.santimattius.template.ui.models.MovieUiModel

class PopularMoviesAdapter(private val onItemClick: (MovieUiModel) -> Unit = {}) :
    ListAdapter<MovieUiModel, MovieViewHolder>(MovieDiffItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}


