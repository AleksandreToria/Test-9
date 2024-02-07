package com.example.test8.presentation.event

sealed class HomeEvent {
    data object FetchItems : HomeEvent()
    data object ResetErrorMessage : HomeEvent()
}