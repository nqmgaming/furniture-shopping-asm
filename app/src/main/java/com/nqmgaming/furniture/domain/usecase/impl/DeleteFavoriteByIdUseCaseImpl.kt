package com.nqmgaming.furniture.domain.usecase.impl

import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.DeleteFavoriteByIdUseCase
import javax.inject.Inject

class DeleteFavoriteByIdUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : DeleteFavoriteByIdUseCase {
    override suspend fun execute(input: DeleteFavoriteByIdUseCase.Input): DeleteFavoriteByIdUseCase.Output {
        return try {
            favoriteRepository.deleteFavorite(input.favoriteId)
            DeleteFavoriteByIdUseCase.Output.Success(Unit)
        } catch (e: Exception) {
            DeleteFavoriteByIdUseCase.Output.Failure
        }
    }
}