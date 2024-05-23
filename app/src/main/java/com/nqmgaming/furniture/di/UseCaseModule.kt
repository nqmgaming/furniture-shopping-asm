package com.nqmgaming.furniture.di

import com.nqmgaming.furniture.domain.usecase.AddFavoriteUseCase
import com.nqmgaming.furniture.domain.usecase.DeleteFavoriteByIdUseCase
import com.nqmgaming.furniture.domain.usecase.GetFavoritesUseCase
import com.nqmgaming.furniture.domain.usecase.GetUserInfoUseCase
import com.nqmgaming.furniture.domain.usecase.LogInUseCase
import com.nqmgaming.furniture.domain.usecase.SignUpUseCase
import com.nqmgaming.furniture.domain.usecase.impl.AddFavoriteUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.DeleteFavoriteByIdUseCaseImpl
import com.nqmgaming.furniture.domain.usecase.impl.GetFavoritesUseCaseImpl
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

    @Binds
    abstract fun addFavoriteUseCase(impl: AddFavoriteUseCaseImpl): AddFavoriteUseCase

    @Binds
    abstract fun bindDeleteFavoriteByIdUseCase(impl: DeleteFavoriteByIdUseCaseImpl): DeleteFavoriteByIdUseCase
    @Binds
    abstract fun bindGetFavoriteUseCase(impl: GetFavoritesUseCaseImpl): GetFavoritesUseCase

    @Binds
    abstract fun bindGetUserInfoUseCase(impl: GetUserInfoUseCaseImpl): GetUserInfoUseCase

    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindLogInUseCase(impl: LogInUseCaseImpl): LogInUseCase
}