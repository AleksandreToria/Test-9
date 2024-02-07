package com.example.test8.domain.repository

import com.example.test8.data.remote.common.Resource
import com.example.test8.domain.model.GetItems
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItems(): Flow<Resource<List<GetItems>>>
}