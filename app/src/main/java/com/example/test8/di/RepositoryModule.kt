package com.example.test8.di

import com.example.test8.data.remote.common.HandleResponse
import com.example.test8.data.remote.repository.ItemRepositoryImpl
import com.example.test8.data.remote.service.ItemService
import com.example.test8.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object
RepositoryModule {
    @Singleton
    @Provides
    fun provideConnectionsRepository(
        itemService: ItemService,
        handleResponse: HandleResponse
    ): ItemRepository {
        return ItemRepositoryImpl(
            itemService = itemService,
            handleResponse = handleResponse
        )
    }
}