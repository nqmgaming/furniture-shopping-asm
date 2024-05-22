package com.nqmgaming.furniture.domain.model.product

import androidx.compose.ui.graphics.Color


data class Product(
    val productId: Int,
    val name: String,
    val price: Int,
    val description: String,
    val colors: List<String>,
    val images: List<String>,
    val createdAt: String,
    val categoryId: Int
) {
    fun colorFromString(string: String): Color {
        return when (string) {
            "red" -> Color.Red
            "green" -> Color.Green
            "blue" -> Color.Blue
            "white" -> Color.White
            "black" -> Color.Black
            "orange" -> Color(0xFFFFA500)
            "purple" -> Color(0xFF800080)
            "indigo" -> Color(0xFF4B0082)
            "teal" -> Color(0xFF008080)
            "grey" -> Color.Gray
            "amber" -> Color(0xFFFFC107)
            "cyan" -> Color.Cyan
            "brown" -> Color(0xFF795548)
            "lime" -> Color(0xFFCDDC39)
            "pink" -> Color(0xFFE91E63)
            "yellow" -> Color.Yellow
            else -> Color.White
        }
    }

    fun colorToString(color: Color): String {
        return when (color) {
            Color.Red -> "red"
            Color.Green -> "green"
            Color.Blue -> "blue"
            Color.White -> "white"
            Color.Black -> "black"
            Color(0xFFFFA500) -> "orange"
            Color(0xFF800080) -> "purple"
            Color(0xFF4B0082) -> "indigo"
            Color(0xFF008080) -> "teal"
            Color.Gray -> "grey"
            Color(0xFFFFC107) -> "amber"
            Color.Cyan -> "cyan"
            Color(0xFF795548) -> "brown"
            Color(0xFFCDDC39) -> "lime"
            Color(0xFFE91E63) -> "pink"
            Color.Yellow -> "yellow"
            else -> "white"
        }
    }
}
