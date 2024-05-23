package com.nqmgaming.furniture.domain.usecase

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.network.dto.FavoriteRequest
import com.nqmgaming.furniture.domain.model.favorite.Favorite

interface GetFavoriteById: UseCase<GetFavoriteById.Input, GetFavoriteById.Output> {
    data class Input(
        val userId : Int,
        val productId: Int
    )

    sealed class Output {
        data class Success(val favoriteRequest: FavoriteRequest): Output()
        data object Failure: Output()
    }
}