package com.example.test8.presentation.mapper

import com.example.test8.domain.model.GetItems
import com.example.test8.presentation.model.Item

fun GetItems.toPresenter() =
    Item(
        id = id,
        cover = cover,
        price = price,
        title = title,
        location = location,
        reactionCount = reactionCount,
        rate = rate
    )