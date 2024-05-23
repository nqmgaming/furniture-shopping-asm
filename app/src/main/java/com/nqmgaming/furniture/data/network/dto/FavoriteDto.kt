package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteDto(
    @SerialName("favorite_id")
    val favoriteId: Int,
    @SerialName("product_id")
    val productId: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("Products")
    val productDto: ProductDto?,
    @SerialName("Users")
    val userDto: UserDto?
)
