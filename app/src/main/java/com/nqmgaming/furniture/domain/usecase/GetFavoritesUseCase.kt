package com.nqmgaming.furniture.domain.usecase

import com.nqmgaming.furniture.data.network.dto.FavoriteDto

interface GetFavoritesUseCase : UseCase<GetFavoritesUseCase.Input, GetFavoritesUseCase.Output> {
    class Input(val userId: Int)
    sealed class Output() {
        data class Success(val data: List<FavoriteDto>) : Output()
        data object Failure : Output()
    }
}