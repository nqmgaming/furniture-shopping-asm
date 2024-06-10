package com.nqmgaming.furniture.di

import com.nqmgaming.furniture.domain.repository.AuthenticationRepository
import com.nqmgaming.furniture.domain.repository.CartRepository
import com.nqmgaming.furniture.domain.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.repository.ProductRepository
import com.nqmgaming.furniture.data.repository.AuthenticationRepositoryImpl
import com.nqmgaming.furniture.data.repository.CartRepositoryImpl
import com.nqmgaming.furniture.data.repository.FavoriteRepositoryImpl
import com.nqmgaming.furniture.data.repository.OrderRepositoryImpl
import com.nqmgaming.furniture.data.repository.ProductRepositoryImpl
import com.nqmgaming.furniture.domain.repository.OrderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun cartRepository(impl: CartRepositoryImpl): CartRepository

    @Binds
    abstract fun bindFavoriteRepository(impl: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindAuthenticateRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    abstract fun bindOrderRepository(impl: OrderRepositoryImpl): OrderRepository

}