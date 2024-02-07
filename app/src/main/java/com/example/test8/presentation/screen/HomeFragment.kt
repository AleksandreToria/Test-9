package com.example.test8.presentation.screen

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.test8.databinding.FragmentHomeBinding
import com.example.test8.presentation.base.BaseFragment
import com.example.test8.presentation.event.HomeEvent
import com.example.test8.presentation.extension.showSnackBar
import com.example.test8.presentation.state.HomeState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: HomeFragmentPagerAdapter

    override fun bind() {
        adapter = HomeFragmentPagerAdapter()
        binding.pager.adapter = adapter

        viewModel.onEvent(HomeEvent.FetchItems)
    }

    override fun bindViewActionListeners() {

    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeState.collect {
                    handleHomeState(it)
                }
            }
        }
    }

    private fun handleHomeState(state: HomeState) {
        binding.progress.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.items?.let {
            (binding.pager.adapter as? HomeFragmentPagerAdapter)?.submitList(it)
        }

        state.errorMessage?.let {
            binding.root.showSnackBar(message = it)
            viewModel.onEvent(HomeEvent.ResetErrorMessage)
        }
    }
}