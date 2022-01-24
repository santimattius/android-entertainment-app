package com.santimattius.template.presentation.adapters.diff

import androidx.recyclerview.widget.DiffUtil
import com.santimattius.template.presentation.models.MovieUiModel

class MovieDiffItem : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.identifier == newItem.identifier
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }
}