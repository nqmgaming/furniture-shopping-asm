package com.nqmgaming.furniture.data.repository.impl

import android.util.Log
import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.repository.FavoriteRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
) : FavoriteRepository {
    override suspend fun getFavoritesByUserId(userId: Int): List<FavoriteDto> {
        return try {
            val result = postgrest.from("Favorites")
                .select(
                    columns = Columns.list("*", "Products(*), Users(*)")
                ) {
                    filter {
                        eq("user_id", userId)
                    }
                }.decodeList<FavoriteDto>()
            Log.d("FavoriteRepositoryImpl", "getFavoritesByUserId: $result")
            result
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getFavoritesByFavoriteId(favoriteId: Int): FavoriteDto {
        return try {
            val result = postgrest.from("Favorites")
                .select(
                    columns = Columns.list("*", "Products(*), Users(*)")
                ) {
                    filter {
                        eq("favorite_id", favoriteId)
                    }
                }.decodeSingle<FavoriteDto>()
            result
        } catch (e: Exception) {
            e.printStackTrace()
            FavoriteDto(0, 0, 0, null, null)
        }
    }

    override suspend fun addFavorite(userId: Int, productId: Int) {
        try {
            postgrest.from("Favorites")
                .insert(
                    buildJsonObject {
                        put("user_id", userId)
                        put("product_id", productId)
                    }
                )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteFavorite(favoriteId: Int) {
        try {
            postgrest.from("Favorites")
                .delete {
                    filter {
                        eq("favorite_id", favoriteId)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}