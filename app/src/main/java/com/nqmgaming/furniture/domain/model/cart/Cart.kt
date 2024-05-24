package com.nqmgaming.furniture.domain.model.cart

import com.nqmgaming.furniture.domain.model.product.Product

data class Cart(
    val cartId: Int,
    val quantity: Int,
    val colorString: String,
    val productId: Int,
    var product: Product = Product()
)
