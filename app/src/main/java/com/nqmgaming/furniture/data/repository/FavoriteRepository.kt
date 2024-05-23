package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.FavoriteDto

interface FavoriteRepository {
    suspend fun getFavoritesByUserId(userId: Int): List<FavoriteDto>
    suspend fun getFavoritesByFavoriteId(favoriteId: Int): FavoriteDto
    suspend fun addFavorite(userId: Int, productId: Int)
    suspend fun deleteFavorite(favoriteId: Int)
}