package com.nqmgaming.furniture.domain.usecase.impl

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.repository.FavoriteRepository
import com.nqmgaming.furniture.domain.usecase.GetFavoriteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoriteUseCaseImpl @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : GetFavoriteUseCase {
    override suspend fun execute(input: GetFavoriteUseCase.Input): GetFavoriteUseCase.Output {
        return withContext(Dispatchers.IO) {
            val result = favoriteRepository.getFavoritesByUserId(input.userId)
            result.let { it ->
                GetFavoriteUseCase.Output.Success(data = it.map {
                    FavoriteDto(
                        favoriteId = it.favoriteId,
                        userId = it.userId,
                        productId = it.productId,
                        productDto = it.productDto,
                        userDto = it.userDto
                    )
                })
            } ?: GetFavoriteUseCase.Output.Failure
        }
    }
}