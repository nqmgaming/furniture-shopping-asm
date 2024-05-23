package com.nqmgaming.furniture.domain.usecase.impl

import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.GetFavoriteById
import javax.inject.Inject

class GetFavoriteByIdImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : GetFavoriteById {
    override suspend fun execute(input: GetFavoriteById.Input): GetFavoriteById.Output {
        val result = favoriteRepository.getIsFavorite(input.userId, input.productId)
        return if (result.favoriteId != 0) {
            GetFavoriteById.Output.Success(result)
        } else {
            GetFavoriteById.Output.Failure
        }
    }
}