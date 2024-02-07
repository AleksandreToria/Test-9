package com.example.test8.data.remote.model

import com.squareup.moshi.Json

data class ItemDto(
    val id: Int,
    val cover: String,
    val price: String,
    val title: String,
    val location: String,
    @Json(name = "reaction_count")
    val reactionCount: Int,
    val rate: Int?
)
