package com.nqmgaming.furniture.di

import com.nqmgaming.furniture.domain.usecase.cart.AddCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.DecrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartByIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.GetCartsByUserIdUseCase
import com.nqmgaming.furniture.domain.usecase.cart.IncrementQuantityCartUseCase
import com.nqmgaming.furniture.domain.usecase.cart.RemoveCartItemUseCase
import com.nqmgaming.furniture.domain.usecase.favorite.GetFavoritesUseCase
import com.nqmgaming.furniture.domain.usecase.product.GetProductByIdUseCase
import com.nqmgaming.furniture.domain.usecase.user.GetUserInfoUseCase
import com.nqmgaming.furniture.domain.usecase.user.LogInUseCase
import com.nqmgaming.furniture.domain.usecase.user.SignUpUseCase
import com.nqmgaming.furniture.domain.usecase.favorite.UpdateFavoritesUseCase
import com.nqmgaming.furniture.domain.usecase.impl.cart.AddCartUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.cart.DecrementQuantityCartUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.cart.GetCartByIdUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.cart.GetCartsByUserIdUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.cart.IncrementQuantityCartUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.cart.RemoveCartItemUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.favorite.GetFavoritesUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.product.GetProductByIdUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.user.GetUserInfoUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.user.LogInUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.user.SignUpUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.favorite.UpdateFavoritesUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.order.CreateOrderUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.user.CreateUserUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.order.CreateOrderUseCase
import com.nqmgaming.furniture.domain.usecase.user.CreateUserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

    // This is the part for the order use cases
    @Binds
    abstract fun bindAddOrderUseCase(impl: CreateOrderUseCaseImpl): CreateOrderUseCase

    // This is the part for the cart use cases

    @Binds
    abstract fun bindAddCartUseCase(impl: AddCartUseCaseImpl): AddCartUseCase

    @Binds
    abstract fun bindDecrementQuantityCartUseCase(impl: DecrementQuantityCartUseCaseImpl): DecrementQuantityCartUseCase

    @Binds
    abstract fun bindIncrementQuantityCartUseCase(impl: IncrementQuantityCartUseCaseImpl): IncrementQuantityCartUseCase

    @Binds
    abstract fun bindRemoveCartItemUseCase(impl: RemoveCartItemUseCaseImpl): RemoveCartItemUseCase

    @Binds
    abstract fun bindGetCartByIdUserUseCase(impl: GetCartsByUserIdUseCaseImpl): GetCartsByUserIdUseCase

    @Binds
    abstract fun bindGetCartByIdUseCase(impl: GetCartByIdUseCaseImpl): GetCartByIdUseCase

    // This is the part for the product use cases
    @Binds
    abstract fun bindGetProductByIdUseCase(impl: GetProductByIdUseCaseImpl): GetProductByIdUseCase

    // This is the part for the favorite use cases
    @Binds
    abstract fun bindUpdateFavoritesUseCase(impl: UpdateFavoritesUseCaseImpl): UpdateFavoritesUseCase

    @Binds
    abstract fun bindGetFavoriteUseCase(impl: GetFavoritesUseCaseImpl): GetFavoritesUseCase


    // This is the part for the user use cases
    @Binds
    abstract fun bindGetUserInfoUseCase(impl: GetUserInfoUseCaseImpl): GetUserInfoUseCase

    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindLogInUseCase(impl: LogInUseCaseImpl): LogInUseCase

    @Binds
    abstract fun bindInsertUserUseCase(impl: CreateUserUseCaseImpl): CreateUserUseCase
}