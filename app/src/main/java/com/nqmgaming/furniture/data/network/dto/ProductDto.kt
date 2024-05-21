package com.nqmgaming.furniture.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("product_id")
    val productId: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("price")
    val price: Int = 0,
    @SerialName("categoryId")
    val categoryId: Int = 0,
    @SerialName("color_list")
    val colorList: List<String> = listOf(),
    @SerialName("image_list")
    val imageList: List<String> = listOf(),
    @SerialName("created_at")
    val createdAt: String = "",
)