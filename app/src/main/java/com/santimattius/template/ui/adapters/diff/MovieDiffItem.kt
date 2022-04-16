package com.santimattius.template.ui.adapters.diff

import androidx.recyclerview.widget.DiffUtil
import com.santimattius.template.ui.models.MovieUiModel

class MovieDiffItem : DiffUtil.ItemCallback<MovieUiModel>() {
    override fun areItemsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieUiModel, newItem: MovieUiModel): Boolean {
        return oldItem == newItem
    }
}