package com.nqmgaming.furniture.data.repository

import com.nqmgaming.furniture.data.network.dto.OrderDto
import com.nqmgaming.furniture.domain.repository.OrderRepository
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
) : OrderRepository {
    override suspend fun createOrder(order: OrderDto): Boolean {
        return try {
            postgrest.from("Orders")
                .insert(order)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}