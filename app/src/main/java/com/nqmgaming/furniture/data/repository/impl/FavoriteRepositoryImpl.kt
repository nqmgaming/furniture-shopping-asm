package com.nqmgaming.furniture.data.repository.impl

import com.nqmgaming.furniture.data.network.dto.FavoriteDto
import com.nqmgaming.furniture.data.repository.FavoriteRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
) : FavoriteRepository {
    override suspend fun getFavoriteList(userId: Int): FavoriteDto {
        return try {
            withContext(Dispatchers.IO) {
                val result = postgrest.from("Users")
                    .select(
                        columns = Columns.list("favorite_list")
                    ) {
                        filter {
                            eq("user_id", userId)
                        }
                    }.decodeSingle<FavoriteDto>()
                result
            }
        } catch (
            e: Exception
        ) {
            e.printStackTrace()
            FavoriteDto(emptyList())
        }

    }

    override suspend fun updateFavoriteList(userId: Int, favoriteList: FavoriteDto) {
        return try {
            withContext(Dispatchers.IO) {
                val result = postgrest.from("Users")
                    .update(
                        {
                            set("favorite_list", favoriteList.favoriteList)
                        }
                    ) {
                        filter {
                            eq("user_id", userId)
                        }
                    }



            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}