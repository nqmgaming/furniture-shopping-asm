package com.nqmgaming.furniture.domain.usecase.impl.favorite

import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.favorite.UpdateFavoritesUseCase
import javax.inject.Inject

class UpdateFavoritesUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : UpdateFavoritesUseCase {
    override suspend fun execute(input: UpdateFavoritesUseCase.Input): UpdateFavoritesUseCase.Output {
        return try {
            favoriteRepository.updateFavoriteList(input.userId, input.favoriteList)
            UpdateFavoritesUseCase.Output.Success(input.favoriteList.favoriteList)
        } catch (e: Exception) {
            UpdateFavoritesUseCase.Output.Failure
        }
    }

}