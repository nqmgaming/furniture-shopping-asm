package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("product_id")
    val productId: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: Int,
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("color_list")
    val colorList: List<String>,
    @SerialName("image_list")
    val imageList: List<String>,
    @SerialName("created_at")
    val createdAt: String,
)