package com.nqmgaming.furniture.domain.repository

import com.nqmgaming.furniture.data.network.dto.ProductDto

interface ProductRepository {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getProductById(productId: Int): ProductDto
    suspend fun searchProduct(query: String): List<ProductDto>
}