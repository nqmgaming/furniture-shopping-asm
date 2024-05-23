package com.nqmgaming.furniture.domain.usecase.impl

import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.GetFavoritesUseCase
import com.nqmgaming.furniture.domain.usecase.GetUserInfoUseCase
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