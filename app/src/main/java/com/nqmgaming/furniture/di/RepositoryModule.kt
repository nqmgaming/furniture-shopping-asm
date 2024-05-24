package com.nqmgaming.furniture.di

import com.nqmgaming.furniture.data.repository.AuthenticationRepository
import com.nqmgaming.furniture.data.repository.CartRepository
import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.data.repository.ProductRepository
import com.nqmgaming.furniture.data.repository.impl.AuthenticationRepositoryImpl
import com.nqmgaming.furniture.data.repository.impl.CartRepositoryImpl
import com.nqmgaming.furniture.data.repository.impl.FavoriteRepositoryImpl
import com.nqmgaming.furniture.data.repository.impl.ProductRepositoryImpl
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

}