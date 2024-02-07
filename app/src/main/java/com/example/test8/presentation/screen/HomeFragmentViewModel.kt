package com.example.test8.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test8.data.remote.common.Resource
import com.example.test8.domain.usecase.GetItemsUseCase
import com.example.test8.presentation.event.HomeEvent
import com.example.test8.presentation.mapper.toPresenter
import com.example.test8.presentation.state.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.FetchItems -> fetchItems()
            HomeEvent.ResetErrorMessage -> updateErrorMessage(message = null)
        }
    }

    private fun fetchItems() {
        viewModelScope.launch {
            getItemsUseCase().collect { it ->
                when (it) {
                    is Resource.Error -> updateErrorMessage(message = it.errorMessage)

                    is Resource.Loading -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }

                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                items = it.data.map {
                                    it.toPresenter()
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _homeState.update { currentState -> currentState.copy(errorMessage = message) }
    }
}