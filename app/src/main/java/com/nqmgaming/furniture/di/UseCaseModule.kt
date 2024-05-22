package com.nqmgaming.furniture.di

import com.nqmgaming.furniture.domain.usecase.GetUserInfoUseCase
import com.nqmgaming.furniture.domain.usecase.LogInUseCase
import com.nqmgaming.furniture.domain.usecase.SignUpUseCase
import com.nqmgaming.furniture.domain.usecase.impl.GetUserInfoUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.LogInUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

//    @Binds
//    abstract fun bindGetProductsUseCase(impl: GetProductsUseCaseImpl): GetProductsUseCase
//
//    @Binds
//    abstract fun bindCreateProductUseCase(impl: CreateProductUseCaseImpl): CreateProductUseCase
//
//    @Binds
//    abstract fun bindGetProductDetailsUseCase(impl: GetProductDetailsUseCaseImpl): GetProductDetailsUseCase
//
//    @Binds
//    abstract fun bindDeleteProductUseCase(impl: DeleteProductUseCaseImpl): DeleteProductUseCase
//
//    @Binds
//    abstract fun bindUpdateProductUseCase(impl: UpdateProductUseCaseImpl): UpdateProductUseCase
//
    @Binds
    abstract fun bindGetUserInfoUseCase(impl: GetUserInfoUseCaseImpl): GetUserInfoUseCase

    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindLogInUseCase(impl: LogInUseCaseImpl): LogInUseCase
}