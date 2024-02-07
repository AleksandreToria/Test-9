package com.example.test8.data.remote.service

import com.example.test8.data.remote.model.ItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ItemService {
    @GET("0545ddc1-c487-46ce-b70c-5b95270d6b76")
    suspend fun getItems(): Response<List<ItemDto>>
}