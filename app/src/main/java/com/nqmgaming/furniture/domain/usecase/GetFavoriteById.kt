package com.nqmgaming.furniture.domain.usecase

import com.nqmgaming.furniture.domain.model.favorite.Favorite

interface GetFavoriteById: UseCase<GetFavoriteById.Input, GetFavoriteById.Output> {
    data class Input(
        val favoriteId: Int
    )

    sealed class Output {
        data class Success(val favorite: Favorite): Output()
        data object Failure: Output()
    }
}