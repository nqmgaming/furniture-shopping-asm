package com.nqmgaming.furniture.domain.repository

import com.nqmgaming.furniture.data.network.dto.FavoriteDto

interface FavoriteRepository {
   suspend fun getFavoriteList(userId: Int): FavoriteDto
   suspend fun updateFavoriteList(userId: Int, favoriteList: FavoriteDto)
}