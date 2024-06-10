package com.nqmgaming.furniture.domain.repository

import com.nqmgaming.furniture.data.network.dto.OrderDto

interface OrderRepository {
    suspend fun createOrder(order: OrderDto): Boolean
}