package com.nqmgaming.furniture.domain.mapper

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.domain.model.cart.Cart

fun CartDto.asDomainModel(): Cart {
    return Cart(
        cartId = cartId,
        productId = productId,
        quantity = quantity,
        colorString = colorString,
        userId = userId
    )
}

fun Cart.asDtoModel(): CartDto {
    return CartDto(
        cartId = cartId,
        productId = productId,
        quantity = quantity,
        colorString = colorString,
        userId = userId
    )
}