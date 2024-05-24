package com.nqmgaming.furniture.domain.usecase.favorite

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.domain.usecase.UseCase

interface GetFavoritesUseCase : UseCase<GetFavoritesUseCase.Input, GetFavoritesUseCase.Output> {
    class Input(val userId: Int)
    sealed class Output() {
        data class Success(val favorites: FavoriteDto) : Output()
        data object Failure : Output()
    }
}