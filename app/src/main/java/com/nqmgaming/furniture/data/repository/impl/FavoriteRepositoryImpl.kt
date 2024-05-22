package com.nqmgaming.furniture.data.repository.impl

import android.util.Log
import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.repository.FavoriteRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.storage.Storage
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
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
                }.
                decodeList<FavoriteDto>()
            Log.d("FavoriteRepositoryImpl", "getFavoritesByUserId: $result")
            result
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun addFavorite(favoriteDto: FavoriteDto) {
        try {
            postgrest.from("Favorites")
                .insert(favoriteDto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteFavorite(favoriteId: Int) {
        TODO("Not yet implemented")
    }
}