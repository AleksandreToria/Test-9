package com.example.test8.domain.model

data class GetItems(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val location: String,
    val reactionCount: Int,
    val rate: Int?
)