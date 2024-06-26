package com.nqmgaming.furniture.data.mapper

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.domain.model.cart.Cart

fun CartDto.asDomainModel(): Cart {
    return Cart(
        cartId = cartId,
        productId = productId,
        quantity = quantity,
        colorString = colorString,
        userId = userId,
        product = product.asDomainModel()
    )
}

fun Cart.asDtoModel(): CartDto {
    return CartDto(
        cartId = cartId,
        productId = productId,
        quantity = quantity,
        colorString = colorString,
        userId = userId,
        product = product.asDtoModel()
    )
}