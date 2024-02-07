package com.example.test8.presentation.state

import com.example.test8.presentation.model.Item


data class HomeState(
    val isLoading: Boolean = false,
    val items: List<Item>? = null,
    val errorMessage: String? = null
)