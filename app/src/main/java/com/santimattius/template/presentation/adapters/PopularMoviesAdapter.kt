package com.santimattius.template.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.santimattius.template.presentation.adapters.diff.MovieDiffItem
import com.santimattius.template.presentation.adapters.viewholders.MovieViewHolder
import com.santimattius.template.presentation.models.MovieUiModel

class PopularMoviesAdapter(private val onItemClick: (MovieUiModel) -> Unit = {}) :
    ListAdapter<MovieUiModel, MovieViewHolder>(MovieDiffItem()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }
}



