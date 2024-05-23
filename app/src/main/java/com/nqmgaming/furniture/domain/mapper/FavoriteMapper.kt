package com.nqmgaming.furniture.domain.mapper

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.domain.model.favorite.Favorite

fun FavoriteDto.asDomainModel() = Favorite(
    favoriteList = favoriteList
)

fun Favorite.asDtoModel() = FavoriteDto(
    favoriteList = favoriteList
)