package com.nqmgaming.furniture.domain.usecase.impl

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.GetFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : GetFavoritesUseCase {
    override suspend fun execute(input: GetFavoritesUseCase.Input): GetFavoritesUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = favoriteRepository.getFavoritesByUserId(input.userId)
            result.let { it ->
                GetFavoritesUseCase.Output.Success(data = it.map {
                    FavoriteDto(
                        favoriteId = it.favoriteId,
                        userId = it.userId,
                        productId = it.productId,
                        productDto = it.productDto,
                        userDto = it.userDto
                    )
                })
            } ?: GetFavoritesUseCase.Output.Failure
        }
    }
}