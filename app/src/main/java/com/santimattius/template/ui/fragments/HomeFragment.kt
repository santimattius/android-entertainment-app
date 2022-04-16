package com.santimattius.template.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.santimattius.template.R
import com.santimattius.template.core.presentation.DialogAction
import com.santimattius.template.core.presentation.openLink
import com.santimattius.template.core.presentation.showDialog
import com.santimattius.template.databinding.PopularMoviesFragmentBinding

import com.santimattius.template.ui.adapters.PopularMoviesAdapter
import com.santimattius.template.ui.viewmodels.HomeState
import com.santimattius.template.ui.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private val homeAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter { openLink(it.imageUrl) }
    }

    private lateinit var viewBinding: PopularMoviesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = PopularMoviesFragmentBinding.inflate(inflater, container, false).apply {
            with(this.gridOfMovies) {
                this.layoutManager = GridLayoutManager(this.context, SPAN_ITEMS)
                this.adapter = homeAdapter
            }
        }
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, ::onStateChange)
    }

    private fun onStateChange(state: HomeState) {
        when (state) {
            is HomeState.Data -> {
                loading(visible = false)
                viewBinding.textEmptyResult.isVisible = state.values.isEmpty()
                homeAdapter.submitList(state.values)
            }
            HomeState.Error -> {
                loading(visible = false)
                showError()
            }
            HomeState.Loading -> {
                loading(visible = true)
            }
        }
    }

    private fun showError() {
        showDialog(
            message = getString(R.string.message_loading_error),
            positiveAction = DialogAction(text = getString(R.string.button_text_positive_error)) {
                viewModel.retry()
            },
            negativeAction = DialogAction(text = getString(R.string.button_text_negative_error)) {
                requireActivity().finish()
            }
        )
    }

    private fun loading(visible: Boolean) = run { viewBinding.homeProgressBar.isVisible = visible }

    companion object {
        private const val SPAN_ITEMS = 2
    }
}