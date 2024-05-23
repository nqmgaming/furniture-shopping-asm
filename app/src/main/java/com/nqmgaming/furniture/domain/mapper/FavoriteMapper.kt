package com.nqmgaming.furniture.domain.mapper

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.domain.model.favorite.Favorite

fun FavoriteDto.asDomainModel(): Favorite {
    return Favorite(
        favoriteId = favoriteId,
        user = userDto.asDomainModel(),
        product = productDto.asDomainModel()
    )
}

fun Favorite.asDtoModel(): FavoriteDto {
    return FavoriteDto(
        favoriteId = favoriteId,
        userId = user.userId,
        productId = product.productId
    )
}