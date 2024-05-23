package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.network.dto.FavoriteRequest

interface FavoriteRepository {
    suspend fun getFavoritesByUserId(userId: Int): List<FavoriteDto>
    suspend fun getIsFavorite(userId: Int, productId: Int): FavoriteRequest
    suspend fun addFavorite(userId: Int, productId: Int)
    suspend fun deleteFavorite(favoriteId: Int)
}