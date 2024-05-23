package com.nqmgaming.furniture.domain.usecase.impl

import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.AddFavoriteUseCase
import javax.inject.Inject

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : AddFavoriteUseCase {
    override suspend fun execute(input: AddFavoriteUseCase.Input): AddFavoriteUseCase.Output {
        return try {
            favoriteRepository.addFavorite(input.userId, input.productId)
            AddFavoriteUseCase.Output.Success(Unit)
        } catch (e: Exception) {
            AddFavoriteUseCase.Output.Failure
        }
    }

}