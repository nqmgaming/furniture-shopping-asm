package com.nqmgaming.furniture.domain.model.category

import androidx.annotation.DrawableRes
import com.nqmgaming.furniture.R

data class Category(
    val id: Int = 0,
    @DrawableRes val icon: Int = R.drawable.ic_popular,
    val title: String = ""
) {
    fun categoriesList(): List<Category> {
        return listOf(
            Category(
                id = 0,
                icon = R.drawable.ic_popular,
                title = "Popular"
            ),
            Category(
                id = 1,
                icon = R.drawable.ic_chair,
                title = "Chair"
            ),
            Category(
                id = 2,
                icon = R.drawable.ic_armchair,
                title = "Armchair"
            ),
            Category(
                id = 3,
                icon = R.drawable.ic_table,
                title = "Table"
            ),
            Category(
                id = 4,
                icon = R.drawable.ic_bed,
                title = "Bed"
            ),
            Category(
                id = 5,
                icon = R.drawable.ic_lamp,
                title = "Lamp"
            ),
        )

    }

}
