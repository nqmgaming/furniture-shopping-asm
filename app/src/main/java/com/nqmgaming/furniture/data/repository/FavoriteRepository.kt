package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.FavoriteDto

interface FavoriteRepository {
    suspend fun getFavoritesByUserId(userId: Int): List<FavoriteDto>
    suspend fun addFavorite(favoriteDto: FavoriteDto)
    suspend fun deleteFavorite(favoriteId: Int)
}