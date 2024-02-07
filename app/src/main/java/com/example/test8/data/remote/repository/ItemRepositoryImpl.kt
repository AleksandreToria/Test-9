package com.example.test8.data.remote.repository

import com.example.test8.data.remote.common.HandleResponse
import com.example.test8.data.remote.common.Resource
import com.example.test8.data.remote.mapper.base.asResource
import com.example.test8.data.remote.mapper.item.toDomain
import com.example.test8.data.remote.service.ItemService
import com.example.test8.domain.model.GetItems
import com.example.test8.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService,
    private val handleResponse: HandleResponse
) : ItemRepository {
    override suspend fun getItems(): Flow<Resource<List<GetItems>>> {
        return handleResponse.apiCall {
            itemService.getItems()
        }.asResource { it ->
            it.map {
                it.toDomain()
            }
        }
    }
}