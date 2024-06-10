package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.ProductDto
import com.nqmgaming.furniture.domain.repository.ProductRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
) : ProductRepository {
    override suspend fun getProducts(): List<ProductDto> {
        return try {
            withContext(Dispatchers.IO) {
                val result = postgrest.from("Products")
                    .select(
                        columns = Columns.list(
                            "product_id",
                            "name",
                            "price",
                            "image_list",
                            "categoryId",
                            "color_list"
                        )
                    )
                    .decodeList<ProductDto>()
                result
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getProductById(productId: Int): ProductDto {
        return try {
            withContext(Dispatchers.IO) {
                val result = postgrest.from("Products")
                    .select {
                        filter {
                            eq("product_id", productId)
                        }
                    }.decodeSingle<ProductDto>()
                result
            }
        } catch (e: Exception) {
            e.printStackTrace()
            ProductDto(0, "", "", 0, 0, emptyList(), emptyList(), "")
        }
    }

    override suspend fun searchProduct(query: String): List<ProductDto> {
        return try {
            withContext(Dispatchers.IO) {
                val result = postgrest.from("Products")
                    .select {
                        filter {
                            ilike("name", "%$query%")
                        }
                    }.decodeList<ProductDto>()
                result
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}