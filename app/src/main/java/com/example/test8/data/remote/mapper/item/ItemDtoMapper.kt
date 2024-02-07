package com.example.test8.data.remote.mapper.item

import com.example.test8.data.remote.model.ItemDto
import com.example.test8.domain.model.GetItems

fun ItemDto.toDomain() =
    GetItems(
        id = id,
        cover = cover,
        price = price,
        title = title,
        location = location,
        reactionCount = reactionCount,
        rate = rate
    )