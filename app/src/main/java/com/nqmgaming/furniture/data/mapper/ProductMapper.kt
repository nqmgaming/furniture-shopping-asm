package com.nqmgaming.furniture.data.mapper

import com.nqmgaming.furniture.data.network.dto.ProductDto
import com.nqmgaming.furniture.domain.model.product.Product

fun ProductDto.asDomainModel(): Product{
    return Product(
        productId = productId,
        name = name,
        description = description,
        price = price,
        categoryId = categoryId,
        colors = colorList,
        images = imageList,
        createdAt = createdAt
    )
}

fun Product.asDtoModel(): ProductDto{
    return ProductDto(
        productId = productId,
        name = name,
        description = description,
        price = price,
        categoryId = categoryId,
        colorList = colors,
        imageList = images,
        createdAt = createdAt
    )
}