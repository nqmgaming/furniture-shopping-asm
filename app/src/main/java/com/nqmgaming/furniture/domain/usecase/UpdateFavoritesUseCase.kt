package com.nqmgaming.furniture.domain.usecase

import com.nqmgaming.furniture.data.network.dto.FavoriteDto

interface UpdateFavoritesUseCase:UseCase<UpdateFavoritesUseCase.Input, UpdateFavoritesUseCase.Output> {
    class Input(val userId: Int, val favoriteList: FavoriteDto)
    sealed class Output {
        data class Success(val favoriteList: List<String>) : Output()
        data object Failure : Output()
    }
}