package com.nqmgaming.furniture.domain.model.favorite

import com.nqmgaming.furniture.domain.model.product.Product
import com.nqmgaming.furniture.domain.model.user.User

data class Favorite(
    val favoriteId: Int,
    val user: User,
    val product: Product?,
    val userId: Int,
    val productId: Int
)
