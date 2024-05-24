package com.nqmgaming.furniture.domain.usecase.impl.favorite

import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.favorite.GetFavoritesUseCase
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : GetFavoritesUseCase {
    override suspend fun execute(input: GetFavoritesUseCase.Input): GetFavoritesUseCase.Output {
        return try {
            val result = favoriteRepository.getFavoriteList(input.userId)
            GetFavoritesUseCase.Output.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            GetFavoritesUseCase.Output.Failure
        }
    }


}